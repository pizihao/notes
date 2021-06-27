package helloworld.liu.as;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: JVMDome
 * @description:
 * @author: liuwenhao
 * @create: 2021-03-10 16:39
 **/
public class Soft01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        System.out.println(a);
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                num = Math.max(num, map.get(s.charAt(i)));
            }
            map.put(s.charAt(i), i);
            count = Math.max(count, i - num);
        }
        return count;
    }

    public boolean verifyPostorder(int[] postorder) {
        return helper(postorder, 0, postorder.length - 1);
    }

    public static boolean helper(int[] pos, int left, int right) {
        if (left >= right) {
            return true;
        }
        int midcount = left;

        //分开左右树
        while (pos[midcount] < pos[right]) {
            midcount++;
        }
        int count = midcount;
        while (pos[midcount] > pos[right]) {
            midcount++;
        }
        boolean leftflag = helper(pos, midcount, count - 1);
        boolean rightflag = helper(pos, count, right - 1);

        return midcount == count && leftflag && rightflag;
    }

    /**
     * @param bags   背包容量 列
     * @param count  物品总数 行
     * @param value  每个的价值
     * @param weight 每个的重量
     * @return int
     * @description: TODO
     * @author liuwenaho
     * @date 2020/11/12 20:03
     */
    public static int bag(int bags, int count, int[] value, int[] weight) {
        int[][] dp = new int[count + 1][bags + 1];
        for (int i = 1; i < count + 1; i++) {
            for (int j = 1; j < bags + 1; j++) {
                if (weight[i - 1] > j){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j - weight[i-1]] + value[i-1]);
                }
            }
        }
        return dp[count][bags];
    }


    
    

}