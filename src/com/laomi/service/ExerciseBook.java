package com.laomi.service;

import com.laomi.bo.Expression;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExerciseBook {


    public static void generateFile(List<Expression> expressions) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        LocalDateTime now = LocalDateTime.now();
        String current = dtf.format(now);

        // 生成问卷
        StringBuilder question = new StringBuilder();
        for (int i = 0; i < expressions.size(); i++) {
            Expression e = expressions.get(i);
            question.append("" + (i + 1) + ". " + e + " =" + "\r\n");
        }

        // 生成答案
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < expressions.size(); i++) {
            Expression e = expressions.get(i);
            answer.append("" + (i + 1) + ". " + e.getUltimateAnswer() + "\r\n");
        }

        try {
            // 问卷写入文件
            File file = new File(System.getProperty("user.dir") + "/" + "Exercises_" + current + ".txt");
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.append(question);

            // 答案写入文件
            file = new File(System.getProperty("user.dir") + "/" + "Answer_" + current + ".txt");
            ps = new PrintStream(new FileOutputStream(file));
            ps.append(answer);// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void checkAnswers(String exerciseFile, String userAnswerPath) {

        List<String> Correct = new ArrayList<>();
        List<String> False = new ArrayList<>();
        File excersice = new File(exerciseFile);
        String answer_timestamp = exerciseFile.substring(exerciseFile.lastIndexOf("_") + 1, exerciseFile.lastIndexOf("."));

        File answer = new File(System.getProperty("user.dir") + "/" + "Answer_" + answer_timestamp + ".txt");

        try {

            FileReader readuser = new FileReader(new File(userAnswerPath));
            FileReader readanswer = new FileReader(answer);
            BufferedReader bufferuser = new BufferedReader(readuser);
            BufferedReader bufferanswer = new BufferedReader(readanswer);
            String s_answer = null;
            String s_user = null;
            while ((s_answer = bufferanswer.readLine()) != null && (s_user = bufferuser.readLine()) != null) {
                if (s_answer.equals(s_user)) {
                    Correct.add(s_answer.substring(0, s_answer.lastIndexOf(".")));
                } else {
                    False.add(s_answer.substring(0, s_answer.lastIndexOf(".")));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File Grade = new File(System.getProperty("user.dir") + "/" + "Grade" + ".txt");
        StringBuilder s_grade = new StringBuilder("Correct: ");
        s_grade.append(Correct.size() + "(");
        for (int i = 0; i < Correct.size(); i++) {
            s_grade.append(Correct.get(i) + ",");
        }
        s_grade.append(")" + "\n");

        s_grade.append("Wrong: ");
        s_grade.append(False.size() + "(");

        for (int i = 0; i < False.size(); i++) {
            s_grade.append(False.get(i) + ",");
        }
        s_grade.append(")" + "\n");

        String tograde = s_grade.toString();
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(Grade));
            ps.append(tograde);// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
