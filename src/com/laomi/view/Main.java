package com.laomi.view;

import com.laomi.bo.Expression;
import com.laomi.service.ExerciseBook;
import com.laomi.service.Producer;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author zkyyo, laomi233
 * @since 2018-09-23 20:48
 **/
public class Main {
    private static final String AMOUNT = "-n";

    private static final String NUMBER_BOUND = "-r";

    private static final String EXERCISE_FILE = "-e";

    private static final String ANSWER_FILE = "-a";

    public static void main(String[] args) {
        if (checkArgs(args, "-n", "-r")) {
            generate(args);
        } else if (checkArgs(args, "-e", "-a")) {
            correct(args);
        } else {
            System.out.println("参数不合法");
        }
    }

    private static void generate(String[] args) {
        int amount = -1;
        int numberBound = -1;
        try {
            amount = Integer.parseInt(args[findArgIndex(args, AMOUNT)]);
            numberBound = Integer.parseInt(args[findArgIndex(args, NUMBER_BOUND)]);
        } catch (Exception ignore) {
            System.out.println("参数不合法");
            System.exit(-1);
        }
        if (amount == -1 || numberBound == -1) {
            System.out.println("参数不合法");
            System.exit(-1);
        }

        Producer producer = new Producer();
        producer.setAmount(amount);
        producer.setNumberBound(numberBound);
        // 生成表达式
        Set<Expression> expressions = producer.produce();
        // 分别生成算术表达式和答案文件

        ExerciseBook.generateFile(new ArrayList<>(expressions));
        System.out.println("成功生成 " + amount + " 道算术题");
    }

    private static void correct(String[] args) {
        String exerciseFile = null;
        String answerFile = null;
        try {
            exerciseFile = args[findArgIndex(args, EXERCISE_FILE)];
            answerFile = args[findArgIndex(args, ANSWER_FILE)];
        } catch (Exception ignore) {
            System.out.println("参数不合法");
            System.exit(-1);
        }

        ExerciseBook.checkAnswers(exerciseFile, answerFile);
        System.out.println("分数已批改完毕 d=====(￣▽￣*)b");
    }

    private static boolean checkArgs(String[] args, String...target) {
        if (target == null || args == null) {
            return false;
        }
        for (String t : target) {
            boolean exist = false;
            for (String arg : args) {
                if (t.equals(arg)) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                return false;
            }
        }
        return true;
    }

    private static int findArgIndex(String[] args, String target) {
        if (target == null || args == null) {
            return -1;
        }
        for (int i = 0; i < args.length; i++) {
            if (target.equals(args[i])) {
                return i + 1;
            }
        }
        return -1;
    }
}
