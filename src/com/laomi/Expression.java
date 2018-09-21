package com.laomi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zkyyo, laomi233
 * @since 2018-09-19 13:41
 **/
public class Expression {
    private Number[] nums;
    private String[] ultimateNumber;
    private char[] ops;
    private int len;
    private int[][] parenthesis;
    private boolean isCorrect;
    private double answer;

    public Expression(int len) {
        this.len = len;
        this.nums = new Number[len];
        this.ops = new char[len - 1];
        this.parenthesis = new int[len][2];
        this.ultimateNumber = new String[len];
        this.isCorrect = true;
    }

    public Number[] getNums() {
        return nums;
    }

    public void setNums(Number[] nums) {
        this.nums = nums;
    }

    public char[] getOps() {
        return ops;
    }

    public void setOps(char[] ops) {
        this.ops = ops;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int[][] getParenthesis() {
        return parenthesis;
    }

    public void setParenthesis(int[][] parenthesis) {
        this.parenthesis = parenthesis;
    }

    public String[] getUltimateNumber() {
        return ultimateNumber;
    }

    public void setUltimateNumber(String[] ultimateNumber) {
        this.ultimateNumber = ultimateNumber;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        if (!Double.isNaN(answer) && !Double.isInfinite(answer)) {
            this.answer = BigDecimal.valueOf(answer).setScale(1, RoundingMode.HALF_UP).doubleValue();
        }
    }

    public List<String> zhangting() {
        List<String> ting = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            int left = parenthesis[i][0];
            int right = parenthesis[i][1];
            while (left > 0) {
                ting.add("(");
                left--;
            }
            ting.add(nums[i] + "");
            while (right > 0) {
                ting.add(")");
                right--;
            }
            if (i < len - 1) {
                ting.add(ops[i] + "");
            }
        }
        return ting;
    }

    public String printOrigin() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int left = parenthesis[i][0];
            int right = parenthesis[i][1];
            while (left > 0) {
                s.append("(");
                left--;
            }
            s.append(nums[i]).append(" ");
            while (right > 0) {
                s = new StringBuilder(s.toString().trim());
                s.append(") ");
                right--;
            }
            if (i < len - 1) {
                s.append(ops[i]).append(" ");
            }
        }
        return s.toString().trim();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < len; i++) {
            while (parenthesis[i][0] > 0) {
                s.append("(");
                parenthesis[i][0]--;
            }
            s.append(ultimateNumber[i]).append(" ");
            while (parenthesis[i][1] > 0) {
                s = new StringBuilder(s.toString().trim());
                s.append(") ");
                parenthesis[i][1]--;
            }
            if (i < len - 1) {
                s.append(ops[i]).append(" ");
            }
        }
        return s.toString();
    }
}
