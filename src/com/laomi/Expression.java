package com.laomi;

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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < len; i++) {
            while (parenthesis[i][0] > 0) {
                s.append("(");
                parenthesis[i][0]--;
            }
            s.append(nums[i]).append(" ");
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
