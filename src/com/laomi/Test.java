package com.laomi;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zkyyo
 * @since 2018-09-19 14:20
 **/
public class Test {
    public static void main(String[] args) {
        int[][] a = new int[10][2];
        int[][] b = Arrays.copyOf(a, a.length);
        a[1][1] = 999;

    }
}
