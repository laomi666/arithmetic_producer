package com.laomi.service;

import com.laomi.bo.Expression;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.lang.Math.min;

public class Fraction {

    public static String[] elements;


    //最大公约数
    private static Integer gcd(Integer a, Integer b)
    {
        if(a==0){
            return b;
        }
        return gcd(b%a,a);
    }

    private static void getGCD(Integer a, Integer b)
    {
        int gcd = gcd(a,b);
        if(gcd!=0) {
            a /= gcd(a,b);
            b /= gcd(a,b);
        }
    }
    //最小公倍数
    private static Integer lcm(Integer a, Integer b)
    {
        if(gcd(a,b)!=0)
        {
            return a*b/gcd(a,b);
        }
        else
        {
            return 0;
        }

    }

    /**
     * 将随机生成的小数改为分数
     * @param number
     * @return String
     */

    public static String toFraction(double number)
    {

        int zhenshu = 0;
        zhenshu = (int)Math.floor(number);
        //System.out.println(zhenshu);
        double decimal = number-zhenshu;
        //System.out.println(decimal);
        int cnt = 0;
        Integer all = 0;
        Integer base = 1;
        String s = (BigDecimal.valueOf(decimal).toString());
        int start = s.indexOf(".")+1;
        int len = min(s.length()-start,start+1);
        for(int i=start;i<=len;i++)
        {
            all = all+(Integer)(s.charAt(i)-'0')*base;
            base = base*10;
        }
        String res = null;
        Integer g = gcd(all,base);
        if(g!=0) {
            all /= g;
            base /= g;
            String frac = all + "/" + base;
            if(all==0)
            {
                res = ""+zhenshu;
            }
            else {
                res = fractionAdd(String.valueOf(zhenshu), frac);
            }
        }
        return res;
    }

    /**
     * 将分数改成真分数
     * @param number
     * @return String
     */
    public static String toRealFraction(String number)
    {
        String res = null;
        if(number.contains("/"))
        {
            int top = Integer.parseInt(number.substring(0,number.indexOf("/")));
            int down = Integer.parseInt(number.substring(number.indexOf("/")+1));
            if(top>down)
            {
                int zhenshu = top/down;
                int res_top = top-zhenshu*down;
                int res_down = down;
                if(res_top!=0)
                {
                    int g = gcd(res_top,res_down);
                    res_top/=g;
                    res_down/=g;
                    res = zhenshu+"'"+res_top+"/"+res_down;
                }
                else{
                    res = ""+zhenshu;
                }
            }
            else
            {
                int g = gcd(top,down);
                if(g!=0)
                {
                    top/=g;
                    down/=g;
                }

                res = top+"/"+down;
            }
            if(top==0)
            {
                res = "0";
            }
        }
       // System.out.println(res);
        return res;
    }

    /**
     * 将Expression的表达式中的算子出现的小数类型转换为真分数
     * @param e
     */
    public static void convertToFraction(Expression e)
    {
        int len  = e.getLen();
        elements = new String[len];
        for(int i=0;i<len;i++)
        {
            if(e.getNums()[i] instanceof Double) {
                elements[i] = toFraction((double) e.getNums()[i]);
            }
            else
            {
                elements[i] = e.getNums()[i].toString();
            }
        }

     }

    /**
     * 比较分数大小
     * @param pre
     * @param next
     * @return String
     *
     */
     public static String fractionCompare(String pre, String next)
     {
         String ans = null;
         if(!pre.contains("/")&&!next.contains("/"))
         {
             Integer pre_ = Integer.parseInt(pre);
             Integer next_ = Integer.parseInt(next);

             if(pre_.equals(next_))
             {
                 ans = "=";
             }
             else if(pre_>next_)
             {
                 ans = ">";
             }
             else if(pre_<next_)
             {
                 ans = "<";
             }
         }
         else if(pre.contains("/")&&next.contains("/"))
         {
             Integer res_top = null;
             Integer res_down = null;
             Integer pre_top = Integer.parseInt(pre.substring(0, pre.indexOf("/")));
             Integer pre_down = Integer.parseInt(pre.substring(pre.indexOf("/") + 1));
             Integer next_top = Integer.parseInt(next.substring(0, next.indexOf("/")));
             Integer next_down = Integer.parseInt(next.substring(next.indexOf("/") + 1));

             res_down = lcm(pre_down, next_down);
             next_top *= (res_down / next_down);
             pre_top *= (res_down / pre_down);
             if(pre_top>next_top)
             {
                 ans = ">";
             }
             else if(pre_top<next_top)
             {
                 ans = "<";
             }
             else if(pre_top.equals(next_top))
             {
                 ans = "=";
             }
         }
         else if(!pre.contains("/")&&next.contains("/"))
         {
             Integer next_top = Integer.parseInt(next.substring(0, next.indexOf("/")));
             Integer next_down = Integer.parseInt(next.substring(next.indexOf("/") + 1));
             Integer pre_top = Integer.parseInt(pre)*next_down;
             if(pre_top>next_top)
             {
                 ans = ">";
             }
             else if(pre_top<next_top)
             {
                 ans = "<";
             }
             else
             {
                 ans = "=";
             }
         }
         else if(pre.contains("/")&&!next.contains("/"))
         {
             Integer pre_top = Integer.parseInt(pre.substring(0, pre.indexOf("/")));
             Integer pre_down = Integer.parseInt(pre.substring(pre.indexOf("/") + 1));
             Integer next_top = Integer.parseInt(next)*pre_down;

             if(pre_top>next_top)
             {
                 ans = ">";
             }
             else if(pre_top<next_top)
             {
                 ans = "<";
             }
             else
             {
                 ans = "=";
             }
         }
         return ans;
     }

    /**
     * 模拟分数加法
     * @param pre
     * @param next
     * @return String
     */
     public static String fractionAdd(String pre, String next)
     {
         Integer res_top = null;
         Integer res_down = null;
         if(pre.contains("/")&&next.contains("/")) {
             Integer pre_top = Integer.parseInt(pre.substring(0, pre.indexOf("/")));
             Integer pre_down = Integer.parseInt(pre.substring(pre.indexOf("/") + 1));
             Integer next_top = Integer.parseInt(next.substring(0, next.indexOf("/")));
             Integer next_down = Integer.parseInt(next.substring(next.indexOf("/") + 1));

             res_down = lcm(pre_down, next_down);
             pre_top *= (res_down / pre_down);
             next_top *= (res_down / next_down);
             res_top = pre_top + next_top;


         }
         else if(!pre.contains("/")&&!next.contains("/"))
         {
             String res = String.valueOf(Integer.parseInt(pre)+Integer.parseInt(next));
             return res;
         }
         else if(pre.contains("/")&&!next.contains("/"))
         {
             Integer pre_top = Integer.parseInt(pre.substring(0,pre.indexOf("/")));
             Integer pre_down = Integer.parseInt(pre.substring(pre.indexOf("/")+1));

             res_down = pre_down;
             res_top = pre_top+Integer.parseInt(next)*pre_down;
             getGCD(res_top,res_down);

         }
         else if(!pre.contains("/")&&next.contains("/"))
         {
             Integer next_top = Integer.parseInt(next.substring(0,next.indexOf("/")));
             Integer next_down = Integer.parseInt(next.substring(next.indexOf("/")+1));

              res_down = next_down;
              res_top = next_top+Integer.parseInt(pre)*next_down;

              getGCD(res_top,res_down);

         }
         if(res_top==null||res_down==null)
         {
             return "0";
         }
         else {
         return res_top+"/"+res_down;}
     }

    /**
     * 模拟分数减法
     * @param pre
     * @param next
     * @return String
     */
     public static String fractionSubstract(String pre, String next)
     {
         Integer res_top = 0;
         Integer res_down = 0;
         if(pre.contains("/")&&next.contains("/")) {
             Integer pre_top = Integer.parseInt(pre.substring(0, pre.indexOf("/")));
             Integer pre_down = Integer.parseInt(pre.substring(pre.indexOf("/") + 1));
             Integer next_top = Integer.parseInt(next.substring(0, next.indexOf("/")));
             Integer next_down = Integer.parseInt(next.substring(next.indexOf("/") + 1));

             res_down = lcm(pre_down, next_down);
             pre_top *= (res_down / pre_down);
             next_top *= (res_down / next_down);

             res_top = pre_top - next_top;
             getGCD(res_top,res_down);
         }
         else if(!pre.contains("/")&&!next.contains("/"))
         {
             Integer res = Integer.parseInt(pre)-Integer.parseInt(next);
             return ""+res;
         }
         else if(pre.contains("/")&&!next.contains("/"))
         {
             Integer pre_top = Integer.parseInt(pre.substring(0,pre.indexOf("/")));
             Integer pre_down = Integer.parseInt(pre.substring(pre.indexOf("/")+1));

             res_down = pre_down;
             res_top = pre_top-Integer.parseInt(next)*pre_down;
             getGCD(res_top,res_down);

         }
         else if(!pre.contains("/")&&next.contains("/"))
         {
             Integer next_top = Integer.parseInt(next.substring(0,next.indexOf("/")));
             Integer next_down = Integer.parseInt(next.substring(next.indexOf("/")+1));

             res_down = next_down;
             res_top = next_top-Integer.parseInt(pre)*next_down;

             getGCD(res_top,res_down);

         }
         if(res_top==null||res_down==null)
         {
             return "0";
         }
         else {
             return res_top+"/"+res_down;}
     }

    /**
     * 模拟分数乘法
     * @param pre
     * @param next
     * @return String
     */
     public static String fractionMultiply(String pre, String next)
     {
         Integer res_top = 0;
         Integer res_down = 0;

         if(pre.contains("/")&&next.contains("/"))
         {
             Integer pre_top = Integer.parseInt(pre.substring(0,pre.indexOf("/")));
             Integer pre_down = Integer.parseInt(pre.substring(pre.indexOf("/")+1));

             Integer next_top = Integer.parseInt(next.substring(0,next.indexOf("/")));
             Integer next_down = Integer.parseInt(next.substring(next.indexOf("/")+1));

             res_top = pre_top*next_top;
             res_down = pre_down*next_down;

             getGCD(res_top,res_down);
         }
         else if(!pre.contains("/")&&!next.contains("/")){
             Integer res = Integer.parseInt(pre)*Integer.parseInt(next);
             return ""+res;
         }
         else if(!pre.contains("/")&&next.contains("/"))
         {
             Integer next_top = Integer.parseInt(next.substring(0,next.indexOf("/")));
             Integer next_down = Integer.parseInt(next.substring(next.indexOf("/")+1));

             res_top = next_top*Integer.parseInt(pre);
             res_down = next_down;

             getGCD(res_top,res_down);
         }
         else if(pre.contains("/")&&!next.contains("/"))
         {
             Integer pre_top = Integer.parseInt(pre.substring(0,pre.indexOf("/")));
             Integer pre_down = Integer.parseInt(pre.substring(pre.indexOf("/")+1));

             res_top = pre_top*Integer.parseInt(next);
             res_down = pre_down;

             getGCD(res_top,res_down);
         }
         if(res_top==null||res_down==null)
         {
             return "0";
         }
         else {
             return res_top+"/"+res_down;}
     }

    /**
     * moni分数除法
     * @param pre
     * @param next
     * @return String
     */
     public static String fractionDivide(String pre, String next)
     {
         Integer res_top = null;
         Integer res_down = null;
         if(pre.contains("/")&&next.contains("/"))
         {
             Integer next_top = Integer.parseInt(next.substring(next.indexOf("/")+1));
             Integer next_down = Integer.parseInt(next.substring(0,next.indexOf("/")));
             next = next_top+"/"+next_down;
             return fractionMultiply(pre,next);
         }
         else if(!pre.contains("/")&&!next.contains("/"))
         {
             res_top = Integer.parseInt(pre);
             res_down = Integer.parseInt(next);
             getGCD(res_top,res_down);

         }
         else if(!pre.contains("/")&&next.contains("/"))
         {
             Integer next_top = Integer.parseInt(next.substring(next.indexOf("/")+1));
             Integer next_down = Integer.parseInt(next.substring(0,next.indexOf("/")));
             next = next_top+"/"+next_down;
             return fractionMultiply(pre,next);
         }
         else if(pre.contains("/")&&!next.contains("/"))
         {
             Integer pre_top = Integer.parseInt(pre.substring(0,pre.indexOf("/")));
             Integer pre_down = Integer.parseInt(pre.substring(pre.indexOf("/")+1));

             res_down = pre_down*Integer.parseInt(next);
             res_top = pre_top;
             getGCD(res_top,res_down);
         }
         if(res_top==0||res_down==null)
         {
             return null;
         }
         else {
             return res_top+"/"+res_down;}
     }

}
