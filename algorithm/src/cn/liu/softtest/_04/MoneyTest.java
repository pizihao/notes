package cn.liu.softtest._04;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author shidacaizi
 * @date 2020/4/4 7:59
 */
/*
* 4、设计找零钱最佳组合的程序
* 假设商店货品价格(R)皆不大于100元（且为整数），
* 若顾客付款在100元内 (P) ， 求找给顾客最少货币个（张）数？
* （货币面值50元、20元、10 元、5 元、1元四 种 ）
* */
public class MoneyTest {
    public static void main(String[] args) {
        Money money = new Money();
        Scanner in = new Scanner(System.in);
        int R = money.whatRP("请输入货品价格", in);
        int P = money.whatRP("请付款", in);
        if (R > P){
            System.out.println("钱不够？");
            main(args);
        }else{
            Map<String, Integer> map = money.allMoney(P - R);
            map.forEach((s, integer) -> {
                System.out.println(s + "元的找" + integer+"张");
            });
        }
        in.close();
    }
}
