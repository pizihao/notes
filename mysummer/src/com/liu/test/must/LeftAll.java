package com.liu.test.must;

import java.util.ArrayList;
import java.util.List;


public class LeftAll {
    private static int n = 5;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<LeftNext> regulars = new ArrayList<LeftNext>();
        LeftNext g1 = new LeftNext("E", "EAT|T");
        LeftNext g2 = new LeftNext("T", "TMF|F");
        LeftNext g3 = new LeftNext("F", "(E)|i");
        LeftNext g4 = new LeftNext("A", "+|-");
        LeftNext g5 = new LeftNext("M", "*|/");
        regulars.add(g5);
        regulars.add(g4);
        regulars.add(g3);
        regulars.add(g2);
        regulars.add(g1);
        String[] Vn = new String[50];
        Vn[0] = regulars.get(0).getLeft();
        int flag = 0;
        int count = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {

                if (regulars.get(i).getLeft().equals(regulars.get(j).getLeft())) {
                    flag++;
                }
            }
            if (flag == 0) {
                count++;
                Vn[count] = regulars.get(i).getLeft();
            }
            flag = 0;
        }
        subFunction(regulars, Vn, count);
        for (LeftNext reg : regulars) {
            if (reg != null) {
                System.out.println(reg.getLeft() + "---->" + reg.getRight());
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("时间："+(end - start));
    }
    public static void subFunction(List<LeftNext> regulars, String[] Vn, int count) {
        int flag = 0;

        for (int i = 0; i <= count; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < regulars.size(); k++)
                    if (Vn[i].equals(regulars.get(k).getLeft())) {
                        if (regulars.get(k).getRight().substring(0, 1).equals(Vn[j])) {
                            for (int h = 0; h < regulars.size(); h++) {
                                if (regulars.get(h).getLeft().equals(Vn[j])) {
                                    String str;
                                    str = regulars.get(k).getRight().substring(1);
                                    LeftNext reg = new LeftNext();
                                    reg.setLeft(regulars.get(k).getLeft());
                                    reg.setRight(regulars.get(h).getRight() + str);
                                    regulars.add(reg);
                                }
                            }

                        }
                    }
            }
        }

        for (int i = 0; i <= count; i++) {
            flag = 0;
            for (int j = 0; j < regulars.size(); j++) {
                if (regulars.get(j).getLeft().equals(Vn[i])) {

                    if (regulars.get(j).getLeft().equals(regulars.get(j).getRight().substring(0, 1))) {

                        flag++;
                    }
                }
            }
            if (flag != 0) {
                for (int j = 0; j < regulars.size(); j++) {
                    if (regulars.get(j).getLeft().equals(Vn[i])) {
                        if (regulars.get(j).getLeft().equals(regulars.get(j).getRight().substring(0, 1))) {

                            String str = regulars.get(j).getRight().substring(1);
                            String temp = regulars.get(j).getLeft();
                            String temp1 = "'";
                            regulars.get(j).setLeft(temp + temp1);
                            String[] split = str.split("\\|");
                            for (int i1 = 0; i1 < split.length; i1++) {
                                if (i1 != split.length-1){
                                    regulars.get(j).setRight(split[i1] + regulars.get(j).getLeft());
                                }else {
                                    regulars.add(new LeftNext(temp, split[i1] ));
                                }
                            }
                            LeftNext reg = new LeftNext();
                            reg.setLeft(regulars.get(j).getLeft());
                            reg.setRight("ε");
                            regulars.add(reg);

                        } else {
                            String temp = regulars.get(j).getLeft();
                            String temp1 = "'";
                            temp = temp + temp1;
                            regulars.get(j).setRight(regulars.get(j).getRight() + temp);
                        }
                    }
                }
            }
        }
    }
}