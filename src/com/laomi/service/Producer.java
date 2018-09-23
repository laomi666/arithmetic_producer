package com.laomi.service;

import com.laomi.bo.Expression;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
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
    private double parenthesisFactor = 0.7;

    /**
     * 生成分数概率, 范围为 0~1, 数字越大生成分数概率越大
     */
    private double fractionFactor = 0.2;

    /**
     * 参与运算的数值的个数
     */
    private int maxLen = 4;

    /**
     * 各个算数值的最大值(不包括)
     */
    private int numberBound;

    /**
     * 生成表达式的数量
     */
    private int amount;

    public Set<Expression> produce() {
        Set<Expression> expressions = new HashSet<>();
        int count = 0;
        while (count < amount) {
            Expression e = new Expression(ThreadLocalRandom.current().nextInt(2, maxLen + 1));
            for (int i = 0; i < e.getLen(); i++) {
                // 生成运算数
                e.getNums()[i] = getRandomNumber();
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
                //将答案改成分数
                changeAnswerToFraction(e, answer);
            }
            if (e.isCorrect() && !expressions.contains(e)) {
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
     * 生成 0(包括 0)以上的随机数字
     *
     * @return 随机数字
     */
    private Number getRandomNumber() {
        if (new Random().nextDouble() < fractionFactor) {
            double d = ThreadLocalRandom.current().nextDouble(0, numberBound);
            d = BigDecimal.valueOf(d).setScale(1, RoundingMode.HALF_UP).doubleValue();
            if (d == (int) d) {
                return (int) d;
            } else {
                return d;
            }
        } else {
            return new Random().nextInt(numberBound);
        }
    }

    private void polish(Expression e, int start, int end) {
        if (end > start && new Random().nextDouble() < parenthesisFactor) {
            int middle = ThreadLocalRandom.current().nextInt(start, end);
            // 避免在表达式最外层套括号
            if (!(start == 0 && end == e.getLen() - 1)) {
                if (new Random().nextDouble() < parenthesisFactor) {
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
            String num;
            if (zhenshu != 0) {
                num = "" + zhenshu + "'" + numerator + "/" + denominator;
            } else {
                num = numerator + "/" + denominator;
            }
            e.getUltimateNumber()[i] = num;

        }
    }

    private void changeAnswerToFraction(Expression e, double ans) {
        if ((int) ans == ans) {
            e.setUltimateAnswer(("" + (int) ans));
        } else {
            int zhenshu = (int) Math.floor(ans);
            double decimal = ans - (double) zhenshu;
            decimal *= 100;
            BigInteger numerator = BigInteger.valueOf((int) decimal);
            BigInteger denominator = BigInteger.valueOf(100);
            BigInteger g = numerator.gcd(denominator);
            if (numerator.equals(BigInteger.ZERO)) {
                e.setUltimateAnswer("" + zhenshu);
            } else {
                String answer;
                numerator = numerator.divide(g);
                denominator = denominator.divide(g);
                if (zhenshu != 0) {
                    answer = "" + zhenshu + "'" + numerator + "/" + denominator;
                } else {
                    answer = numerator + "/" + denominator;
                }
                e.setUltimateAnswer(answer);

            }
        }
    }

    public double getParenthesisFactor() {
        return parenthesisFactor;
    }

    public void setParenthesisFactor(double parenthesisFactor) {
        this.parenthesisFactor = parenthesisFactor;
    }

    public double getFractionFactor() {
        return fractionFactor;
    }

    public void setFractionFactor(double fractionFactor) {
        this.fractionFactor = fractionFactor;
    }

    public int getMaxLen() {
        return maxLen;
    }

    public void setMaxLen(int maxLen) {
        this.maxLen = maxLen;
    }

    public int getNumberBound() {
        return numberBound;
    }

    public void setNumberBound(int numberBound) {
        this.numberBound = numberBound;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

