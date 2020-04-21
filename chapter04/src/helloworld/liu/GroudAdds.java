package helloworld.liu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author shidacaizi
 * @data 2020/3/11 13:10
 */
public class GroudAdds {
    static int c = 0;
    public static void main(String[] args) {
        // 调用Scanner 方法
        Scanner in1 = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);
        //请求输入，表示有多少堆金币
        int s = Integer.parseInt(in1.nextLine());
        ArrayList<Integer> list = new ArrayList();
        //请求输入，表示每堆金币有几个,并放入一个集合
        for (int i = 0; i < s; i++) {
            list.add(Integer.parseInt(in2.nextLine()));
        }
        numberAll(list);
        System.out.println(c);
    }

    public static void  numberAll(ArrayList<Integer> list){
        //找到相邻相加最小的两堆,这里min初始值需要比题目给定的最大值*2还要大
        int min = 201;
        int falg = 0;
        for (int i = 0; i < list.size()-1; i++) {
            int a = min;
            min = Math.min(min, list.get(i)+list.get(i+1));
            if (a != min){
                falg = i;
            }
        }
        list.remove(falg);
        list.remove(falg);
        list.add(falg, min);
        c = c + min;
        if (list.size() == 2){
            c = c + list.get(0) + list.get(1);
            return;
        }
        numberAll(list);
    }
}
