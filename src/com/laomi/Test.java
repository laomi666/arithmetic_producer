package com.laomi;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zkyyo
 * @since 2018-09-19 14:20
 **/
public class Test {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add(1, "ccc");
        System.out.println(list);
    }
}
