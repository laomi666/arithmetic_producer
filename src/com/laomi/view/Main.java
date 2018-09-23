package com.laomi.view;

import com.laomi.service.ExerciseBook;
import com.laomi.bo.Expression;
import com.laomi.service.Producer;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author zkyyo
 * @since 2018-09-23 20:48
 **/
public class Main {

    public static void main(String[] args) {
        Producer producer = new Producer();
        Set<Expression> expressions = producer.produce(10);
        // 分别生成算术表达式和答案文件
        ExerciseBook.generateFile(new ArrayList<>(expressions));
    }
}
