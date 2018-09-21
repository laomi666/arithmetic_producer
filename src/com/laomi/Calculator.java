package com.laomi;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zkyyo, laomi233
 * @since 2018-09-19 21:45
 **/
public class Calculator {
    public static Double count(List<String> e) {
        Stack<String> stack = new Stack<>();
        for (String arg : e) {
            if (!")".equals(arg)) {
                stack.push(arg);
            } else {
                List<String> subExpression = new LinkedList<>();
                while (!stack.empty()) {
                    if ("(".equals(stack.peek())) {
                        stack.pop();
                        Number result = countWithoutParenthesis(subExpression);
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

//        while (!stack.empty()) {
//            System.out.println(stack.pop());
//        }
        Double result;
        if (stack.size() > 1) {
            List<String> subExpression = new LinkedList<>();
            while (!stack.empty()) {
                subExpression.add(0, stack.pop());
            }
            result = (Double) countWithoutParenthesis(subExpression);
            if (result == null) {
                return null;
            }
        } else {
            result = Double.parseDouble(stack.pop());
        }
        return result;

//        return BigDecimal.valueOf(answer).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    private static Number countWithoutParenthesis(List<String> e) {
        List<String> exp = new CopyOnWriteArrayList<>(e);
        int start = 0;
        int index;
        while ((index = getOperationIndex(exp, start, "x", "÷")) != -1) {
            operation(exp, index);
            start = index - 1;
        }
        start = 0;
        while ((index = getOperationIndex(exp, start, "+", "-")) != -1) {
            if (operation(exp, index) == -1) {
                return null;
            }
            start = index - 1;
        }

        return Double.parseDouble(exp.get(0));
    }

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

    private static int operation(List<String> e, int index) {
        String op = e.get(index);
        double pre = Double.parseDouble(e.get(index - 1));
        double post = Double.parseDouble(e.get(index + 1));
        // 从后往前删除
        e.remove(index + 1);
        e.remove(index);
        e.remove(index - 1);
        switch (op) {
            case "x":
                e.add(index - 1, pre * post + "");
                break;
            case "÷":
                e.add(index - 1, pre / post + "");
                break;
            case "+":
                e.add(index - 1, pre + post + "");
                break;
            case "-":
                if (pre < post) {
                    return -1;
                }
                e.add(index - 1, pre - post + "");
                break;
            default:
                break;
        }
        return 1;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("3", "-", "6", "x", "8", "÷", "(", "3", "-", "6", ")", "x", "3", "+", "4");
//        List<String> list = Arrays.asList("2", "x", "(", "6", "-", "4", ")");
        System.out.println("-----------" + count(list));
    }

}
