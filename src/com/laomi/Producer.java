package com.laomi;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zkyyo, laomi233
 * @since 2018-09-19 13:38
 **/
public class Producer {
    private static final char[] OPERATIONS = {'+', '-', 'x', '÷'};
    /**
     * 生成括号概率, 范围为 0~1, 数值越大生成括号概率越大
     */
    private static final double PARENTHESIS_FACTOR = 0.5;
    /**
     * 生成分数概率, 范围为 0~1, 数字越大生成分数概率越大
     */
    private static final double FRACTION_FACTOR = 0.2;
    /**
     * 各个算数值的最大值(不包括)
     */
    private static final int NUMBER_BOUND = 10;
    /**
     * 数值的个数
     */
    private static final int MAX_LEN = 4;


    public static void main(String[] args){
        Producer producer = new Producer();
        producer.produce(10);
    }

    public List<Expression> produce(int amount) {
        List<Expression> expressions = new LinkedList<>();
        int count = 0;
        while (count < amount) {
            Expression e = new Expression(ThreadLocalRandom.current().nextInt(2, MAX_LEN + 1));
            for (int i = 0; i < e.getLen(); i++) {
                // 生成运算数
                e.getNums()[i] = getRandomNumber(NUMBER_BOUND, FRACTION_FACTOR);
                if (i < e.getLen() - 1) {
                    // 生成运算符号
                    e.getOps()[i] = getRandomOperation();
                }
            }

            // 生成括号
            polish(e, 0, e.getLen() - 1);
            // 小数转换为分数
            changeToFraction(e);
            // 计算答案
            Double answer = Calculator.count(e.zhangting());
            if (answer == null || Double.isNaN(answer) || Double.isInfinite(answer)) {
                e.setCorrect(false);
            } else {
                e.setAnswer(answer);
            }
            if (e.isCorrect()) {
                System.out.println(e + " = " + e.getAnswer());
                expressions.add(e);
                count++;
            }
        }
        return expressions;
    }

    private char getRandomOperation() {
        return OPERATIONS[new Random().nextInt(OPERATIONS.length)];
    }

    /**
     * 生成0(包括0)以上的随机数字
     *
     * @param bound  最大范围(不包含该参数)
     * @param factor 生成浮点数的概率（0~1）, 该参数越大生成浮点数的概率越高
     * @return 随机数字
     */
    private Number getRandomNumber(double bound, double factor) {
        if (new Random().nextDouble() < factor) {
            double d = ThreadLocalRandom.current().nextDouble(0, bound);
            d = BigDecimal.valueOf(d).setScale(1, RoundingMode.HALF_UP).doubleValue();
            if (d == (int) d) {
                return (int) d;
            } else {
                return d;
            }
        } else {
            return new Random().nextInt((int) bound);
        }
    }

    private void polish(Expression e, int start, int end) {
        if (end > start && new Random().nextDouble() < PARENTHESIS_FACTOR) {
            int middle = ThreadLocalRandom.current().nextInt(start, end);
            // 避免在表达式最外层套括号
            if (!(start == 0 && end == e.getLen() - 1)) {
                if (new Random().nextDouble() < PARENTHESIS_FACTOR) {
                    e.getParenthesis()[start][0]++;
                    e.getParenthesis()[end][1]++;
                }
            }
            polish(e, start, middle);
            polish(e, middle + 1, end);
        }
    }

    private void changeToFraction(Expression e) {
        for (int i = 0; i < e.getLen(); i++) {
            if (e.getNums()[i].equals(e.getNums()[i].intValue())) {
                e.getUltimateNumber()[i] = e.getNums()[i].toString();
                continue;
            }
            double temp = (double) e.getNums()[i];
            double decimal = temp - Math.floor(temp);
            decimal *= 100;
            BigInteger numerator = BigInteger.valueOf((int) decimal);
            BigInteger denominator = BigInteger.valueOf(100);
            BigInteger g = denominator.gcd(numerator);
            numerator = numerator.divide(g);
            denominator = denominator.divide(g);
            int zhenshu = (int) Math.floor(temp);
            String num = "" + zhenshu;
            num = num + "'" + numerator.toString() + "/" + denominator.toString();
            e.getUltimateNumber()[i] = num;

        }
    }
}

