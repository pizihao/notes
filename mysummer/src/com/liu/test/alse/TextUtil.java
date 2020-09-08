package com.liu.test.alse;

import java.util.*;

/**
 * @author shidacaizi
 * @date 2020/5/16 22:13
 */

public class TextUtil {
    public static boolean containsbA(TreeSet<Character> nvSet, String itemCharStr, Character a,
                                     HashMap<Character, ArrayList<String>> expressionMap) {
        String aStr = a.toString();
        String lastStr = itemCharStr.substring(itemCharStr.length() - 1);
        if (lastStr.equals(aStr)) {
            return true;
        }
        return false;
    }
    public static boolean containsbAbIsNull(TreeSet<Character> nvSet, String itemCharStr, Character a,
                                            HashMap<Character, ArrayList<String>> expressionMap) {
        String aStr = a.toString();
        if (containsAB(nvSet, itemCharStr, a)) {
            Character alastChar = getAlastChar(itemCharStr, a);
            ArrayList<String> arrayList = expressionMap.get(alastChar);
            if (arrayList.contains("ε")) {
                return true;
            }
        }
        return false;

    }
    public static boolean containsAb(TreeSet<Character> ntSet, String itemCharStr, Character a) {
        String aStr = a.toString();
        if (itemCharStr.contains(aStr)) {
            int aIndex = itemCharStr.indexOf(aStr);
            String findStr;
            try {
                findStr = itemCharStr.substring(aIndex + 1, aIndex + 2);
            } catch (Exception e) {
                return false;
            }
            if (ntSet.contains(findStr.charAt(0))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public static boolean containsAB(TreeSet<Character> nvSet, String itemCharStr, Character a) {
        String aStr = a.toString();
        if (itemCharStr.contains(aStr)) {
            int aIndex = itemCharStr.indexOf(aStr);
            String findStr;
            try {
                findStr = itemCharStr.substring(aIndex + 1, aIndex + 2);
            } catch (Exception e) {
                return false;
            }
            if (nvSet.contains(findStr.charAt(0))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public static Character getAlastChar(String itemCharStr, Character a) {
        String aStr = a.toString();
        if (itemCharStr.contains(aStr)) {
            int aIndex = itemCharStr.indexOf(aStr);
            String findStr = "";
            try {
                findStr = itemCharStr.substring(aIndex + 1, aIndex + 2);
            } catch (Exception e) {
                return null;
            }
            return findStr.charAt(0);
        }
        return null;
    }
    public static boolean isEmptyStart(String selectExp) {
        char charAt = selectExp.charAt(0);
        if (charAt == 'ε') {
            return true;
        }
        return false;
    }
    public static boolean isNtStart(TreeSet<Character> ntSet, String selectExp) {
        char charAt = selectExp.charAt(0);
        if (ntSet.contains(charAt)) {
            return true;
        }
        return false;
    }
    public static boolean isNvStart(TreeSet<Character> nvSet, String selectExp) {
        char charAt = selectExp.charAt(0);
        if (nvSet.contains(charAt)) {
            return true;
        }
        return false;
    }
    public static String findUseExp(TreeMap<Character, HashMap<String, TreeSet<Character>>> selectMap, Character peek,
                                    char charAt) {
        try {
            HashMap<String, TreeSet<Character>> hashMap = selectMap.get(peek);
            Set<String> keySet = hashMap.keySet();
            for (String useExp : keySet) {
                TreeSet<Character> treeSet = hashMap.get(useExp);
                if (treeSet.contains(charAt)) {
                    return useExp;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
