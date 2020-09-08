package com.liu.test.alse;

import java.util.ArrayList;
import java.util.Stack;

/**
*
* @Param:  
* @return:  
* @Author: shidacaizi
* @Date: 2020/5/17 
*/ 
public class Recu {

    private ArrayList<Production> Productions;
    private Grammar ll1Grammar;
    private Character startChar;
    private Stack<Character> analyzeStatck;
    private String str;
    private String useExp;
    public Recu() {
        super();
        analyzeStatck = new Stack<Character>();
        analyzeStatck.push('#');
    }

    public Grammar getLl1Grammar() {
        return ll1Grammar;
    }

    public void setLl1Grammar(Grammar ll1Grammar) {
        this.ll1Grammar = ll1Grammar;
    }

    public ArrayList<Production> getProductions() {
        return Productions;
    }

    public void setProductions(ArrayList<Production> Productions) {
        this.Productions = Productions;
    }

    public Character getStartChar() {
        return startChar;
    }

    public void setStartChar(Character startChar) {
        this.startChar = startChar;
    }

    public Stack<Character> getAnalyzeStatck() {
        return analyzeStatck;
    }

    public void setAnalyzeStatck(Stack<Character> analyzeStatck) {
        this.analyzeStatck = analyzeStatck;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getUseExp() {
        return useExp;
    }

    public void setUseExp(String useExp) {
        this.useExp = useExp;
    }

    public void analyze() {
        Productions = new ArrayList<Production>();

        analyzeStatck.push(startChar);
        System.out.println("开始符:" + startChar);
        int index = 0;

        while (!analyzeStatck.empty()) {
            index++;
            if (analyzeStatck.peek() != str.charAt(0)) {

                String nowUseExpStr = TextUtil.findUseExp(ll1Grammar.getSelectMap(), analyzeStatck.peek(), str.charAt(0));
                System.out.println(index + "\t\t\t" + analyzeStatck.toString() + "\t\t\t" + str + "\t\t\t"
                        + analyzeStatck.peek() + "->" + nowUseExpStr);
                Production produce = new Production();
                produce.setIndex(index);
                produce.setAnalyzeStackStr(analyzeStatck.toString());
                produce.setStr(str);
                if (null == nowUseExpStr) {
                    produce.setUseExpStr("无法匹配!");
                } else {
                    produce.setUseExpStr(analyzeStatck.peek() + "->" + nowUseExpStr);
                }
                Productions.add(produce);

                analyzeStatck.pop();

                if (null != nowUseExpStr && nowUseExpStr.charAt(0) != 'ε') {
                    for (int j = nowUseExpStr.length() - 1; j >= 0; j--) {
                        char currentChar = nowUseExpStr.charAt(j);
                        analyzeStatck.push(currentChar);
                    }
                }
                continue;
            }

            if (analyzeStatck.peek() == str.charAt(0)) {
                System.out.println(index + "\t\t\t" + analyzeStatck.toString() + "\t\t\t" + str + "\t\t\t" + "“"
                        + str.charAt(0) + "”匹配");
                Production produce = new Production();
                produce.setIndex(index);
                produce.setAnalyzeStackStr(analyzeStatck.toString());
                produce.setStr(str);
                produce.setUseExpStr("“" + str.charAt(0) + "”匹配");
                Productions.add(produce);
                analyzeStatck.pop();
                str = str.substring(1);
                continue;
            }
        }

    }

}