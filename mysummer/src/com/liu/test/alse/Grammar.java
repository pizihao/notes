package com.liu.test.alse;

import java.io.Serializable;
import java.util.*;

/**
 * @Param:
 * @return:
 * @Author: shidacaizi
 * @Date: 2020/5/17
 */
public class Grammar implements Serializable {
    private static final long serialVersionUID = 1L;
    private String[][] analyzeTable;
    private TreeMap<Character, HashMap<String, TreeSet<Character>>> selectMap;
    private ArrayList<String> gsArray;
    private HashMap<Character, ArrayList<String>> expressionMap;
    private Character s;
    private TreeSet<Character> nvSet;
    private TreeSet<Character> ntSet;
    private HashMap<Character, TreeSet<Character>> firstMap;
    private HashMap<Character, TreeSet<Character>> followMap;

    public Grammar() {
        super();
        gsArray = new ArrayList<String>();
        nvSet = new TreeSet<Character>();
        ntSet = new TreeSet<Character>();
        firstMap = new HashMap<Character, TreeSet<Character>>();
        followMap = new HashMap<Character, TreeSet<Character>>();
        selectMap = new TreeMap<Character, HashMap<String, TreeSet<Character>>>();
    }

    public String[][] getAnalyzeTable() {
        return analyzeTable;
    }

    public void setAnalyzeTable(String[][] analyzeTable) {
        this.analyzeTable = analyzeTable;
    }

    public TreeMap<Character, HashMap<String, TreeSet<Character>>> getSelectMap() {
        return selectMap;
    }

    public void setSelectMap(TreeMap<Character, HashMap<String, TreeSet<Character>>> selectMap) {
        this.selectMap = selectMap;
    }

    public HashMap<Character, TreeSet<Character>> getFirstMap() {
        return firstMap;
    }

    public void setFirstMap(HashMap<Character, TreeSet<Character>> firstMap) {
        this.firstMap = firstMap;
    }

    public HashMap<Character, TreeSet<Character>> getFollowMap() {
        return followMap;
    }

    public void setFollowMap(HashMap<Character, TreeSet<Character>> followMap) {
        this.followMap = followMap;
    }

    public HashMap<Character, ArrayList<String>> getExpressionMap() {
        return expressionMap;
    }

    public void setExpressionMap(HashMap<Character, ArrayList<String>> expressionMap) {
        this.expressionMap = expressionMap;
    }

    public ArrayList<String> getGsArray() {
        return gsArray;
    }

    public void setGsArray(ArrayList<String> gsArray) {
        this.gsArray = gsArray;
    }

    public Character getS() {
        return s;
    }

    public void setS(Character s) {
        this.s = s;
    }

    public TreeSet<Character> getNvSet() {
        return nvSet;
    }

    public void setNvSet(TreeSet<Character> nvSet) {
        this.nvSet = nvSet;
    }

    public TreeSet<Character> getNtSet() {
        return ntSet;
    }

    public void setNtSet(TreeSet<Character> ntSet) {
        this.ntSet = ntSet;
    }

    public void getNvNt() {
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

    public void initExpressionMaps() {
        expressionMap = new HashMap<Character, ArrayList<String>>();
        for (String gsItem : gsArray) {
            String[] nvNtItem = gsItem.split("->");
            String charItemStr = nvNtItem[0];
            String charItemRightStr = nvNtItem[1];
            char charItem = charItemStr.charAt(0);
            if (!expressionMap.containsKey(charItem)) {
                ArrayList<String> expArr = new ArrayList<String>();
                expArr.add(charItemRightStr);
                expressionMap.put(charItem, expArr);
            } else {
                ArrayList<String> expArr = expressionMap.get(charItem);
                expArr.add(charItemRightStr);
                expressionMap.put(charItem, expArr);
            }
        }
    }

    public void getFirst() {
        Iterator<Character> iterator = nvSet.iterator();
        while (iterator.hasNext()) {
            Character charItem = iterator.next();
            ArrayList<String> arrayList = expressionMap.get(charItem);
            for (String itemStr : arrayList) {
                boolean shouldBreak = false;
                for (int i = 0; i < itemStr.length(); i++) {
                    char itemitemChar = itemStr.charAt(i);
                    TreeSet<Character> itemSet = firstMap.get(charItem);
                    if (null == itemSet) {
                        itemSet = new TreeSet<Character>();
                    }
                    shouldBreak = calcFirst(itemSet, charItem, itemitemChar);
                    if (shouldBreak) {
                        break;
                    }
                }
            }
        }
    }

    private boolean calcFirst(TreeSet<Character> itemSet, Character charItem, char itemitemChar) {
        if (itemitemChar == 'ε' || ntSet.contains(itemitemChar)) {
            itemSet.add(itemitemChar);
            firstMap.put(charItem, itemSet);
            return true;
        } else if (nvSet.contains(itemitemChar)) {
            ArrayList<String> arrayList = expressionMap.get(itemitemChar);
            for (int i = 0; i < arrayList.size(); i++) {
                String string = arrayList.get(i);
                char tempChar = string.charAt(0);
                calcFirst(itemSet, charItem, tempChar);
            }
        }
        return true;
    }

    public void getFollow() {
        for (Character tempKey : nvSet) {
            TreeSet<Character> tempSet = new TreeSet<Character>();
            followMap.put(tempKey, tempSet);
        }
        Iterator<Character> iterator = nvSet.descendingIterator();
        while (iterator.hasNext()) {
            Character charItem = iterator.next();
            Set<Character> keySet = expressionMap.keySet();
            for (Character keyCharItem : keySet) {
                ArrayList<String> charItemArray = expressionMap.get(keyCharItem);
                for (String itemCharStr : charItemArray) {
                    TreeSet<Character> itemSet = followMap.get(charItem);
                    calcFollow(charItem, charItem, keyCharItem, itemCharStr, itemSet);
                }
            }
        }
    }

    private void calcFollow(Character putCharItem, Character charItem, Character keyCharItem, String itemCharStr,
                            TreeSet<Character> itemSet) {
        if (charItem.equals(s)) {
            itemSet.add('#');
            followMap.put(putCharItem, itemSet);
        }
        if (TextUtil.containsAb(ntSet, itemCharStr, charItem)) {
            Character alastChar = TextUtil.getAlastChar(itemCharStr, charItem);
            itemSet.add(alastChar);
            followMap.put(putCharItem, itemSet);
        }
        if (TextUtil.containsAB(nvSet, itemCharStr, charItem)) {
            Character alastChar = TextUtil.getAlastChar(itemCharStr, charItem);
            TreeSet<Character> treeSet = firstMap.get(alastChar);
            itemSet.addAll(treeSet);
            if (treeSet.contains('ε')) {
                itemSet.add('#');
            }
            itemSet.remove('ε');
            followMap.put(putCharItem, itemSet);
            if (TextUtil.containsbAbIsNull(nvSet, itemCharStr, charItem, expressionMap)) {
                char tempChar = TextUtil.getAlastChar(itemCharStr, charItem);
                if (!keyCharItem.equals(charItem)) {
                    Set<Character> keySet = expressionMap.keySet();
                    for (Character keyCharItems : keySet) {
                        ArrayList<String> charItemArray = expressionMap.get(keyCharItems);
                        for (String itemCharStrs : charItemArray) {
                            calcFollow(putCharItem, keyCharItem, keyCharItems, itemCharStrs, itemSet);
                        }
                    }
                }
            }
        }
        if (TextUtil.containsbA(nvSet, itemCharStr, charItem, expressionMap)) {
            if (!keyCharItem.equals(charItem)) {
                Set<Character> keySet = expressionMap.keySet();
                for (Character keyCharItems : keySet) {
                    ArrayList<String> charItemArray = expressionMap.get(keyCharItems);
                    for (String itemCharStrs : charItemArray) {
                        calcFollow(putCharItem, keyCharItem, keyCharItems, itemCharStrs, itemSet);
                    }
                }
            }
        }
    }

    public void getSelect() {
        Set<Character> keySet = expressionMap.keySet();
        for (Character selectKey : keySet) {
            ArrayList<String> arrayList = expressionMap.get(selectKey);
            HashMap<String, TreeSet<Character>> selectItemMap = new HashMap<String, TreeSet<Character>>();
            for (String selectExp : arrayList) {
                TreeSet<Character> selectSet = new TreeSet<Character>();
                if (TextUtil.isEmptyStart(selectExp)) {
                    selectSet = followMap.get(selectKey);
                    selectSet.remove('ε');
                    selectItemMap.put(selectExp, selectSet);
                }
                if (TextUtil.isNtStart(ntSet, selectExp)) {
                    selectSet.add(selectExp.charAt(0));
                    selectSet.remove('ε');
                    selectItemMap.put(selectExp, selectSet);
                }
                if (TextUtil.isNvStart(nvSet, selectExp)) {
                    selectSet = firstMap.get(selectKey);
                    selectSet.remove('ε');
                    selectItemMap.put(selectExp, selectSet);
                }
                selectMap.put(selectKey, selectItemMap);
            }
        }
    }

    public void genAnalyzeTable() throws Exception {
        Object[] ntArray = ntSet.toArray();
        Object[] nvArray = nvSet.toArray();
        analyzeTable = new String[nvArray.length + 1][ntArray.length + 1];
        System.out.println("表");
        System.out.print("Nv/Nt" + "\t\t");
        analyzeTable[0][0] = "Nv/Nt";
        for (int i = 0; i < ntArray.length; i++) {
            if (ntArray[i].equals('ε')) {
                ntArray[i] = '#';
            }
            System.out.print(ntArray[i] + "\t\t");
            analyzeTable[0][i + 1] = ntArray[i] + "";
        }
        System.out.println();
        for (int i = 0; i < nvArray.length; i++) {
            System.out.print(nvArray[i] + "\t\t");
            analyzeTable[i + 1][0] = nvArray[i] + "";
            for (int j = 0; j < ntArray.length; j++) {
                String findUseExp = TextUtil.findUseExp(selectMap, Character.valueOf((Character) nvArray[i]),
                        Character.valueOf((Character) ntArray[j]));
                if (null == findUseExp) {
                    System.out.print("\t\t");
                    analyzeTable[i + 1][j + 1] = "";
                } else {
                    System.out.print(nvArray[i] + "->" + findUseExp + "\t\t");
                    analyzeTable[i + 1][j + 1] = nvArray[i] + "->" + findUseExp;
                }
            }
            System.out.println();
        }
    }

    public void firstMapShow() {
        System.out.println("frist集：");
        this.firstMap.forEach((character, characters) -> {
            System.out.println(character + "{" + characters + "}");
        });
    }

    public void followMapShow() {
        System.out.println("follow集：");
        this.followMap.forEach((character, characters) -> {
            System.out.println(character + "{" + characters + "}");
        });
    }

    public void gsArrayShow() {
        System.out.println("文法：");
        this.gsArray.forEach(System.out::println);
    }
}