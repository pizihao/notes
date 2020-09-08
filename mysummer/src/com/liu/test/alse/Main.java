package com.liu.test.alse;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author shidacaizi
 * @date 2020/5/16 22:16
 */

public class Main {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        ArrayList<String> gsArray = new ArrayList<String>();
        Grammar gs = new Grammar();
        initGs(gsArray);
        gs.setGsArray(gsArray);
        gs.getNvNt();
        gs.initExpressionMaps();
        gs.getFirst();
        gs.setS('E');
        gs.getFollow();
        gs.getSelect();
        Recu analyzer = new Recu();
        analyzer.setStartChar('S');
        analyzer.setLl1Grammar(gs);
        analyzer.setStr("i+i*i#");
        analyzer.analyze();
        gs.genAnalyzeTable();
        System.out.println("");
        gs.firstMapShow();
        gs.followMapShow();
        gs.gsArrayShow();
        long end = System.currentTimeMillis();
        System.out.println("时间："+(end - start));
    }

    private static void getNvNt(ArrayList<String> gsArray, TreeSet<Character> nvSet, TreeSet<Character> ntSet) {
        for (String gsItem : gsArray) {
            String[] nvNtItem = gsItem.split("->");
            String charItemStr = nvNtItem[0];
            char charItem = charItemStr.charAt(0);
            nvSet.add(charItem);
        }
        for (String gsItem : gsArray) {
            String[] nvNtItem = gsItem.split("->");
            String nvItemStr = nvNtItem[1];
            for (int i = 0; i < nvItemStr.length(); i++) {
                char charItem = nvItemStr.charAt(i);
                if (!nvSet.contains(charItem)) {
                    ntSet.add(charItem);
                }
            }
        }

    }

    private static void initGs(ArrayList<String> gsArray) {
        gsArray.add("E->TB");
        gsArray.add("B->ATB");
        gsArray.add("B->ε");
        gsArray.add("T->C");
        gsArray.add("C->MFC");
        gsArray.add("C->ε");
        gsArray.add("F->(E)");
        gsArray.add("F->i");
        gsArray.add("A->+");
        gsArray.add("A->-");
        gsArray.add("M->*");
        gsArray.add("M->/");
    }
}
