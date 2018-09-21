package com.laomi;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcersiceBook {


    private static String timestamp = String.valueOf(System.currentTimeMillis());

    public static File CopyProblems(List<Expression> expressions) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < expressions.size(); i++) {
            Expression e = expressions.get(i);
            stringBuilder.append(""+(i+1)+". "+e + " ="+"\n");
        }


        String input = stringBuilder.toString();
        File Excersices = new File(System.getProperty("user.dir") + "/" + "Exercises_"+timestamp+".txt");
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(Excersices));
            ps.append(input);
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
        File Answer = new File(System.getProperty("user.dir") + "/" + "Answer_"+timestamp+".txt");
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(Answer));
            ps.append(input);// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Answer;
    }

    public static void CheckTheAnswer(String excersicepath,String useranswerpath) throws IOException {

        List<String> Correct = new ArrayList<>();
        List<String> False = new ArrayList<>();
        File excersice = new File(excersicepath);
        String answer_timestamp = excersicepath.substring(excersicepath.lastIndexOf("_")+1,excersicepath.lastIndexOf("."));

        File answer = new File(System.getProperty("user.dir") + "/" + "Answer_"+answer_timestamp+".txt");

        try{

            FileReader readuser = new FileReader(new File(useranswerpath));
            FileReader readanswer = new FileReader(answer);
            BufferedReader bufferuser = new BufferedReader(readuser);
            BufferedReader bufferanswer = new BufferedReader(readanswer);
            String s_answer = null;
            String s_user = null;
            while((s_answer=bufferanswer.readLine())!=null&&(s_user=bufferuser.readLine())!=null)
            {
                if(s_answer.equals(s_user))
                {
                    Correct.add(s_answer.substring(0,s_answer.lastIndexOf(".")));
                }
                else
                {
                    False.add(s_answer.substring(0,s_answer.lastIndexOf(".")));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File Grade = new File(System.getProperty("user.dir")+"/"+"Grade"+".txt");
        StringBuilder s_grade = new StringBuilder("Correct: ");
        s_grade.append(Correct.size()+"(");
        for(int i=0;i<Correct.size();i++)
        {
            s_grade.append(Correct.get(i)+",");
        }
        s_grade.append(")"+"\n");

        s_grade.append("Wrong: ");
        s_grade.append(False.size()+"(");

        for(int i=0;i<False.size();i++)
        {
            s_grade.append(False.get(i)+",");
        }
        s_grade.append(")"+"\n");

        String tograde = s_grade.toString();
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(Grade));
            ps.append(tograde);// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
