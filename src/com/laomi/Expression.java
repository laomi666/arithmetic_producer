package com.laomi;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zkyyo
 * @since 2018-09-19 13:41
 **/
public class Expression {
    private Number[] nums;
    private char[] ops;
    private int len;
    private int[][] parenthesis;

    public Expression(int len) {
        this.len = len;
        this.nums = new Number[len];
        this.ops = new char[len - 1];
        this.parenthesis = new int[len][2];
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

    @Override
    public String toString() {
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
        return s.toString();
    }
}
