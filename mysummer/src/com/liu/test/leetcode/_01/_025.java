package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/9 14:55
 */
//https://leetcode-cn.com/problems/re-space-lcci/
public class _025 {
    public static void main(String[] args) {
        System.out.println(respace(
                new String[]{"looked", "just", "like", "her", "brother"},
                "jesslookedjustliketimherbrother"
        ));
    }
    public static int respace(String[] dictionary, String sentence) {
        if(sentence==null||sentence.length()==0)
            return 0;
        int n=sentence.length();
        if(dictionary==null||dictionary.length==0)
            return n;
        int[] dp=new int[n+1];
        for(int i=1;i<=n;i++){
            dp[i]=dp[i-1]+1;
            for(String word:dictionary){
                int wlen=word.length();
                if(i-wlen>=0&&sentence.substring(i-wlen,i).equals(word)){
                    dp[i]=Math.min(dp[i-wlen],dp[i]);
                }
            }
        }
        return dp[n];
    }
}