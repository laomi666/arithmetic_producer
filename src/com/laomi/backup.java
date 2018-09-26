package com.laomi;

import com.laomi.service.Fraction;

import java.util.LinkedList;
import java.util.List;

public class backup {
    //
//    private void changeToFraction(Expression e) {
//        for (int i = 0; i < e.getLen(); i++) {
//            if (e.getNums()[i].equals(e.getNums()[i].intValue())) {
//                e.getUltimateNumber()[i] = e.getNums()[i].toString();
//                continue;
//            }
//            double temp = (double) e.getNums()[i];
//            double decimal = temp - Math.floor(temp);
//            decimal *= 100;
//            BigInteger numerator = BigInteger.valueOf((int) decimal);
//            BigInteger denominator = BigInteger.valueOf(100);
//            BigInteger g = denominator.gcd(numerator);
//            numerator = numerator.divide(g);
//            denominator = denominator.divide(g);
//            int zhenshu = (int) Math.floor(temp);
//            String num;
//            if (zhenshu != 0) {
//                num = "" + zhenshu + "'" + numerator + "/" + denominator;
//              //  e.setUltimateAnswer(zhenshu+"'"+toFraction(decimal));
//            } else {
//                num = numerator + "/" + denominator;
//                //e.setUltimateAnswer(toFraction(decimal));
//            }
//            e.getUltimateNumber()[i] = num;
//            e.getUltimateNumber()[i] = toFraction(temp);
//
//        }
//    }
//
//    private void changeAnswerToFraction(Expression e, double ans) {
//        if ((int) ans == ans) {
//            e.setUltimateAnswer(("" + (int) ans));
//        } else {
//            int zhenshu = (int) Math.floor(ans);
//            double decimal = ans - (double) zhenshu;
//            //decimal *= 100;
//            BigInteger numerator = BigInteger.valueOf((int) decimal);
//            BigInteger denominator = BigInteger.valueOf(100);
//            BigInteger g = numerator.gcd(denominator);
//            if (numerator.equals(BigInteger.ZERO)) {
//                e.setUltimateAnswer("" + zhenshu);
//            } else {
//                String answer;
//                numerator = numerator.divide(g);
//                denominator = denominator.divide(g);
//                if (zhenshu != 0) {
//                    answer = "" + zhenshu + "'" + numerator + "/" + denominator;
//                    e.setUltimateAnswer(zhenshu +"/"+ toFraction(decimal));
//                } else {
//                    answer = numerator + "/" + denominator;
//                    e.setUltimateAnswer(toFraction(decimal));
//                }
//               // e.setUltimateAnswer(answer);
//            }
//            e.setUltimateAnswer(toFraction(ans));
//        }
//    }
//
//    private Integer gcd(Integer a, Integer b)
//    {
//        if(a%b==0){
//            return b;
//        }
//        return gcd(b,a%b);
//    }
//    private  String toFraction(double number)
//    {
//        int zhenshu = (int)Math.floor(number);
//        //System.out.println(zhenshu);
//        double decimal = number-zhenshu;
//        //System.out.println(decimal);
//        int cnt = 0;
//        Integer all = 0;
//        Integer base = 1;
//        String s = (BigDecimal.valueOf(decimal).toString());
//        int start = s.indexOf(".")+1;
//        for(int i=start;i<min(s.length(),start+2);i++)
//        {
//
//            all = all+(Integer)(s.charAt(i)-'0')*base;
//            base = base*10;
//            cnt++;
//        }
//
//        Integer mina = Integer.MAX_VALUE;
//        Integer minb = Integer.MAX_VALUE;
//
//        int cnt2 = cnt-1;
//
//        for(Integer num = all/10;cnt2>=0; num=num/10,cnt2--)
//        {
//            Integer a = all-num;
//            Integer b = (int)(Math.pow(10,cnt)-Math.pow(10,cnt2));
//            if(b/gcd(a,b)<=minb)
//            {
//                mina = a/gcd(a,b);
//                minb = b/gcd(a,b);
//            }
//
//        }
//        if(zhenshu==0)
//        {
//            return mina+"/"+minb;
//        }
//        else
//        {
//            return zhenshu+"'"+mina+"/"+minb;
//        }
//    }


//
//    public List<String> quetionShowInTrueFraction() {
//
//        List<String> questionInTrue = new LinkedList<>();
//        Fraction.convertToFraction(this);
//        setNumsToString(Fraction.elements);
//        for(int i=0;i<len;i++)
//        {
//            if(numsToString[i].contains("/"))
//            {
//                String exchange = Fraction.toRealFraction(numsToString[i]);
//                if(exchange!=null)
//                {
//                    numsToString[i] = exchange;
//                }
//            }
//        }
//        for (int i = 0; i < len; i++) {
//            int left = parenthesis[i][0];
//            int right = parenthesis[i][1];
//            while (left > 0) {
//                questionInTrue.add("(");
//                left--;
//            }
//            questionInTrue.add(numsToString[i] + "");
//            while (right > 0) {
//                questionInTrue.add(")");
//                right--;
//            }
//            if (i < len - 1) {
//                questionInTrue.add(ops[i] + "");
//            }
//        }
//        return questionInTrue;
//    }
}

