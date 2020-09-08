package com.liu.test.leetcode._01;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shidacaizi
 * @date 2020/6/30 21:24
 */
//https://leetcode-cn.com/problems/valid-anagram/
public class _013 {
    public static void main(String[] args) {
        System.out.println(isAnagram("aacc", "ccac"));
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }
        Map<Character,Integer> maps = new ConcurrentHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            maps.put(s.charAt(i),maps.getOrDefault(s.charAt(i),0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            for (Map.Entry<Character, Integer> entry : maps.entrySet()) {
                Character character = entry.getKey();
                Integer integer = entry.getValue();
                if (character == t.charAt(i)){
                    if (integer == 1){
                        maps.remove(character);
                    }else {
                        maps.replace(character, integer - 1);
                    }
                }
            }
        }
        return maps.isEmpty();
    }
//    public boolean isAnagram(String s, String t) {
//        if(s.length() != t.length())
//            return false;
//        int[] alpha = new int[26];
//        for(int i = 0; i< s.length(); i++) {
//            alpha[s.charAt(i) - 'a'] ++;
//            alpha[t.charAt(i) - 'a'] --;
//        }
//        for(int i=0;i<26;i++)
//            if(alpha[i] != 0)
//                return false;
//        return true;
//    }
}
