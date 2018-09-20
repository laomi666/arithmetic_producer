package com.laomi;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zkyyo
 * @since 2018-09-19 14:20
 **/
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(ThreadLocalRandom.current().nextInt(1, 3));
        }
    }
}
