package com.laomi.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zkyyo, laomi233
 * @since 2018-09-19 21:45
 **/
public class Calculator {
    /**
     * 对表达式进行计算
     * @param e 表达式
     * @return 运算结果
     */
    public static String count(List<String> e) {
        Stack<String> stack = new Stack<>();
        for (String arg : e) {
            if (!")".equals(arg)) {
                stack.push(arg);
            } else {
                List<String> subExpression = new LinkedList<>();
                while (!stack.empty()) {
                    if ("(".equals(stack.peek())) {
                        stack.pop();
                        String result = countWithoutParenthesis(subExpression);
                        if (result == null) {
                            return null;
                        }
                        stack.push(result + "");
                        break;
                    } else {
                        subExpression.add(0, stack.pop());
                    }
                }
            }
        }

        String result;
        if (stack.size() > 1) {
            List<String> subExpression = new LinkedList<>();
            while (!stack.empty()) {
                subExpression.add(0, stack.pop());
            }
            result = countWithoutParenthesis(subExpression);
            if (result == null) {
                return null;
            }
        } else {
            result = stack.pop();
        }
        return result;

    }

    /**
     * 计算没有括号参与的情景, 类似 3 + 2 / 10
     * @param e 表达式
     * @return 运算结果
     */
    private static String countWithoutParenthesis(List<String> e) {
        List<String> exp = new CopyOnWriteArrayList<>(e);
        int start = 0;
        int index;
        while ((index = getOperationIndex(exp, start, "x", "÷")) != -1) {
            if( operation(exp, index) == -1) {
                return null;
            }
            start = index - 1;
        }
        start = 0;
        while ((index = getOperationIndex(exp, start, "+", "-")) != -1) {
            if (operation(exp, index) == -1) {
                return null;
            }
            start = index - 1;
        }

        return exp.get(0);
    }


    /**
     * 获取表达式特定操作符的下标
     * @param e 表达式
     * @param start 从哪里开始获取
     * @param ops 特定的操作符
     * @return 下标
     */
    private static int getOperationIndex(List<String> e, int start, String... ops) {
        for (int i = start; i < e.size(); i++) {
            String arg = e.get(i);
            for (String op : ops) {
                if (op.equals(arg)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 根据操作符对操作数进行操纵
     * @param e 表达式
     * @param index 操作符下标
     * @return 操作成功返回1, 失败返回-1
     */
    private static int operation(List<String> e, int index) {
        String op = e.get(index);


        String pre = e.get(index-1);
        String post = e.get(index+1);


        // 从后往前删除
        e.remove(index + 1);
        e.remove(index);
        e.remove(index - 1);
        switch (op) {
            case "x":
                e.add(index - 1,  Fraction.fractionMultiply(pre,post)+ "");
                break;
            case "÷":
                if (Fraction.fractionCompare(pre,post).equals(">")) { // 避免产生假分数
                    return -1;
                }
                String divide = Fraction.fractionDivide(pre, post);
                if (divide == null) { // 出现除以0的情况
                    return -1;
                }
                e.add(index - 1, divide);
                break;
            case "+":
                e.add(index - 1, Fraction.fractionAdd(pre,post) + "");
                break;
            case "-":
                if (Fraction.fractionCompare(pre,post).equals("<")) { // 避免产生负数
                    return -1;
                }
                e.add(index - 1, Fraction.fractionSubstract(pre,post) + "");
                break;
            default:
                break;
        }
        return 1;
    }
}
