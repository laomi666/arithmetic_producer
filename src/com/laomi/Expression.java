package com.laomi;

/**
 * @author zkyyo
 * @since 2018-09-19 13:41
 **/
public class Expression {
    private Number[] nums;
    private char[] ops;
    private int len;

    public Expression(int len) {
        this.len = len;
        this.nums = new Number[len];
        this.ops = new char[len - 1];
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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < len; i++) {
            s.append(nums[i]).append(" ");
            if (i < len - 1) {
                s.append(ops[i]).append(" ");
            }
        }
        return s.toString();
    }
}
