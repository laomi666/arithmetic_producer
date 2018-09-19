package com.laomi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zkyyo
 * @since 2018-09-19 13:38
 **/
public class Producer {
    private static final char[] OPERATIONS = {'+', '-', 'x', '÷'};

    public static void main(String[] args) throws Exception {
        Producer producer = new Producer();
        for (int j = 0; j < 100000; j++ ) {
//            Expression e = new Expression(ThreadLocalRandom.current().nextInt(1, 3));
            Expression e = new Expression(4);
            for (int i = 0; i < e.getLen(); i++) {
                e.getNums()[i] = producer.getRandomNumber(10, 0.2);
                if (i < e.getLen() - 1) {
                    e.getOps()[i] = producer.getRandomOperation();
                }
            }
            System.out.println(e);
        }
    }

    private char getRandomOperation() {
        return OPERATIONS[new Random().nextInt(OPERATIONS.length)];
    }

    /**
     * 生成0(包括0)以上的随机数字
     *
     * @param bound  最大范围(不包含该参数)
     * @param factor 生成浮点数的概率（0~1）, 该参数越大生成浮点数的概率越高
     * @return
     */
    private Number getRandomNumber(double bound, double factor) {
        if (new Random().nextDouble() < factor) {
            double d = ThreadLocalRandom.current().nextDouble(0, bound);
            return BigDecimal.valueOf(d).setScale(1, RoundingMode.HALF_UP).doubleValue();
        } else {
            return new Random().nextInt((int) bound);
        }
    }

    private int[][]

    private int[][] getParenthesis2(int nums) {
        int[][] parenthesis = new int[nums][2];
        int startIndex = new Random().nextInt(nums - 2);
        int endIndex = startIndex + new Random().nextInt(nums - 1 - startIndex) + 1;
        parenthesis[startIndex][0] = 1;
        parenthesis[endIndex][1] = 1;
        return parenthesis;
    }
}

