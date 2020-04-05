package cn.liu.softtest._04;

import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author shidacaizi
 * @date 2020/4/4 7:59
 */
public class Money {
    private Map<String, Integer> map = new HashMap();
    private int as50 = 0;
    private int as20 = 0;
    private int as10 = 0;
    private int as5 = 0;

    /**
   * 找钱方法
   * @Param: [money]
   * @return: int
   * @Author: shidacaizi
   * @Date: 2020/4/4
   */
    public Map<String, Integer> allMoney(int money){
        while (money > 50){
            this.map.put("50",++as50);
            money = money - 50;
        }
        while (money > 20){
            this.map.put("20",++as20);
            money = money - 20;
        }
        while (money > 10){
            this.map.put("10",++as10);
            money = money - 10;
        }
        while (money > 5){
            this.map.put("5",++as5);
            money = money - 5;
        }
        this.map.put("1",money);
        return this.map;
    }

    /**
    * 输入
    * @Param: [str, in]
    * @return: int 
    * @Author: shidacaizi
    * @Date: 2020/4/4 
    */ 
    public int whatRP(String str, Scanner in){
        System.out.println(str);
        String next = in.next();
        if (isNumeric(next) && Integer.parseInt(next) <= 100 && Integer.parseInt(next) > 0){
            return Integer.parseInt(next);
        }else{
            System.out.println("账单数字不合法");
            whatRP(str, in);
        }
        return 0;
    }

    /**
    * 检验输入的是否是合法数字
    * @Param: [str]
    * @return: boolean
    * @Author: shidacaizi
    * @Date: 2020/4/4
    */
    public boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
