package com.laomi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

public class ExcersiceBook {

    public static File CopyProblems(List<Expression> expressions) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < expressions.size(); i++) {
            Expression e = expressions.get(i);
            stringBuilder.append(""+(i+1)+". "+e + " ="+"\n");
        }
        String input = stringBuilder.toString();
        File Excersices = new File(System.getProperty("user.dir") + "/" + "Exercises.txt");
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(Excersices));
            ps.append(input);// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            }
        return Excersices;
    }

    public static File CopyAnswers(List<Expression> expressions)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < expressions.size(); i++) {
            Expression e = expressions.get(i);
            stringBuilder.append(""+(i+1)+". "+e.getUltimateAnswer()+"\n");
        }
        String input = stringBuilder.toString();
        File Answer = new File(System.getProperty("user.dir") + "/" + "Answer.txt");
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(Answer));
            ps.append(input);// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Answer;
    }

    public static void CheckTheAnswer(String excercisepath,String answerpath)
    {
        File excercises = new File(excercisepath);
        File answer = new File(answerpath);

    }
}
