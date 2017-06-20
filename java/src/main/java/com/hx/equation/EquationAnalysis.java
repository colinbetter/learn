/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.hx.equation;

import java.util.TreeMap;

/**
 * Created by testuser on 17-4-7.
 */
public class EquationAnalysis {
    private TreeMap<Integer, Integer> resultMap = new TreeMap<>();//以次方为键，系数为值

    public static void main(String[] args) {
        EquationAnalysis equationAnalysis = new EquationAnalysis();
        System.out.println("3=0 : " + equationAnalysis.parse("3=0"));
        System.out.println("0=0 : " + equationAnalysis.parse("0=0"));
        System.out.println("3x=0 : " + equationAnalysis.parse("3x=0"));
        System.out.println("0=-x : " + equationAnalysis.parse("0=-x"));
        System.out.println("-x=-x : " + equationAnalysis.parse("-x=-x"));
        System.out.println("x=x : " + equationAnalysis.parse("x=x"));
        System.out.println("3x^2=x : " + equationAnalysis.parse("3x^2=x"));
        System.out.println("3x^2=3x^2 : " + equationAnalysis.parse("3x^2=3x^2"));
        System.out.println("(3x^2)=3x^2 : " + equationAnalysis.parse("(3x^2)=3x^2"));
        System.out.println("(3x^2+2)=3x^2 : " + equationAnalysis.parse("(3x^2+2)=3x^2 "));
        System.out.println("((3x^2+2x))=3x^2 : " + equationAnalysis.parse("((3x^2+2x))=3x^2"));
        System.out.println("((3x^2+2x)-3x)=3x^2 : " + equationAnalysis.parse("((3x^2+2x)-3x)=3x^2"));
        System.out.println("((3x^2+2x)-(3x+1))=3x^2 : " + equationAnalysis.parse("((3x^2+2x)-(3x+1))=3x^2"));
        System.out.println("((3x^2+2x)-(3x+1)+(123x+1))=3x^2 : " + equationAnalysis.parse("((3x^2+2x)-(3x+1)+(123x+1))=3x^2"));
        System.out.println("((3x^2+2x)-(3x+2)+(123x+1))=3x^2 : " + equationAnalysis.parse("((3x^2+2x)-(3x+2)+(123x+1))=3x^2"));
        System.out.println("3x^2=((3x^2+2x)-(3x+2)+(123x+1)) : " + equationAnalysis.parse("3x^2=((3x^2+2x)-(3x+2)+(123x+1))"));
        System.out.println("6x^2=((3x^2+2x)-(3x+2)+(123x+1)) : " + equationAnalysis.parse("6x^2=((3x^2+2x)-(3x+2)+(123x+1))"));
        System.out.println("6x^7=((3x^2+2x)-(3x+2)+(123x+1)) : " + equationAnalysis.parse("6x^7=((3x^2+2x)-(3x+2)+(123x+1))"));
        System.out.println("((3x^2+2x)-(3x+2)+(123x+1))=((3x^2+2x)-(3x+2)+(123x+1)) : " + equationAnalysis.parse("((3x^2+2x)-(3x+2)+(123x+1))=((3x^2+2x)-(3x+2)+(123x+1))"));
        System.out.println("((3x^2+2x)-(3x+2)+(123x+1)-((3x^2+2x)-(3x+2)+(123x+1)))=((3x^2+2x)-(3x+2)+(123x+1)) : "
                + equationAnalysis.parse("((3x^2+2x)-(3x+2)+(123x+1)-((3x^2+2x)-(3x+2)+(123x+1)))=((3x^2+2x)-(3x+2)+(123x+1))"));
    }

    public String parse(String str) {
        resultMap.clear();
        String[] equations = str.split("=");
        doParse(equations[0], true);
        doParse(equations[1], false);
        return getResult();
    }

    /*
    * isPlus表示分析的结果是加还是减*/
    private void doParse(String str, boolean isPlus) {
        while (true) {//依次分析
            if (str == null || str.trim().length() == 0) {
                return;
            }
            switch (str.charAt(0)) {
                case '(': {
                    int end = getRightParenthese(str);//拆分字符串，递归
                    doParse(str.substring(1, end), isPlus);
                    doParse(str.substring(end + 1), isPlus);
                    return;
                }
                case '+':
                    doParse(str.substring(1), isPlus);//加号不影响，递归
                    return;
                case '-': {
                    int end = getPlusOrMinusEnd(str);//分析减号影响范围，递归
                    doParse(str.substring(1, end), !isPlus);
                    doParse(str.substring(end), isPlus);
                    return;
                }
                case 'x': {
                    int value = 1;
                    int key = 1;
                    int i = 1;
                    if (i < str.length() && str.charAt(i) == '^') {
                        key = 0;
                        i++;
                        for (; i < str.length() && isNumber(str.charAt(i)); i++) {
                            key *= 10;
                            key += Integer.valueOf(str.charAt(i) + "");
                        }
                    }
                    Integer oldValue = resultMap.get(key);
                    if (oldValue != null) {
                        value = isPlus ? value + oldValue : oldValue - value;
                    } else {
                        value = isPlus ? value + 0 : 0 - value;
                    }
                    resultMap.put(key, value);
                    str = str.substring(i);//去掉已经分析的字符串
                }
                break;
                default: {//默认是数字开头
                    int i = 0;
                    int value = 0;
                    int key = 0;
                    for (; i < str.length() && isNumber(str.charAt(i)); i++) {//系数
                        value *= 10;
                        value += Integer.valueOf(str.charAt(i) + "");
                    }
                    if (i < str.length() && str.charAt(i) == 'x') {
                        i++;
                        key = 1;
                        if (i < str.length() && str.charAt(i) == '^') {//次方
                            i++;
                            key = 0;
                            for (; i < str.length() && isNumber(str.charAt(i)); i++) {
                                key *= 10;
                                key += Integer.valueOf(str.charAt(i) + "");
                            }
                        }
                    }
                    str = str.substring(i);
                    Integer oldValue = resultMap.get(key);
                    if (oldValue != null) {//有旧值就处理，没有就存入
                        value = isPlus ? value + oldValue : oldValue - value;//判断是加还是减
                    } else {
                        value = isPlus ? value : 0 - value;
                    }
                    resultMap.put(key, value);
                }
                break;

            }
        }
    }

    /*
    * 获取+和-影响的范围
    */
    private int getPlusOrMinusEnd(String str) {
        int location = 1;
        int num = 0;
        while (location < str.length()) {
            switch (str.charAt(location)) {
                case '(':
                    num++;
                    break;
                case ')':
                    num--;
                    break;
                case '+':
                case '-':
                    if (num == 0)
                        return location;
            }
            location++;
        }
        return location;
    }

    /*
    * 获取"（"对应的"）"的位置
    * */
    private int getRightParenthese(String str) {
        int location = 1;
        int num = 1;
        while (location < str.length()) {
            switch (str.charAt(location)) {
                case '(':
                    num++;
                    break;
                case ')':
                    num--;
                    break;
            }
            if (num == 0)
                return location;
            location++;
        }
        return location;
    }

    /*
    * 判断是否是数字
    * */
    private boolean isNumber(char c) {
        if (c >= '0' && c <= '9')
            return true;
        return false;
    }

    /*
    * 结果显示
    * */
    private String getResult() {
        final StringBuilder resultString = new StringBuilder();
        resultMap.forEach((key, value) -> {
            if (value == 0)
                return;
            if (key == 0) {
                if (value > 0)
                    resultString.append("+").append(value);
                else
                    resultString.append(value);
            } else if (key == 1) {
                if (value > 0)
                    resultString.append("+").append(value).append("x");
                else
                    resultString.append(value).append("x");
            } else {
                if (value > 0)
                    resultString.append("+").append(value).append("x^").append(key);
                else
                    resultString.append(value).append("x^").append(key);
            }
        });
        if (resultString.length() == 0)
            return "0=0";
        else if (resultString.charAt(0) == '+')
            resultString.deleteCharAt(0);
        return resultString.append("=0").toString();
    }
}
