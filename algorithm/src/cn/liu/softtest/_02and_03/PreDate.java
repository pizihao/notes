package cn.liu.softtest._02and_03;

import cn.hutool.core.date.DateUtil;

import java.util.Scanner;

/**
 * @author shidacaizi
 * @date 2020/4/3 23:34
 */
public class PreDate {
    // 早一天
    public String inDate(Scanner in){
        System.out.println("请以此输入年 月 日...");
        String year = in.next();
        String month = in.next();
        String day = in.next();
        return DateUtil.formatDate(DateUtil.offsetDay(DateUtil.parse(year + "-" + month + "-" + day), -1));
    }

    // 隔一天
    public String sepaDate(Scanner in){
        System.out.println("请以此输入年 月 日...");
        String year = in.next();
        String month = in.next();
        String day = in.next();
        return DateUtil.formatDate(DateUtil.offsetDay(DateUtil.parse(year + "-" + month + "-" + day), 2));
    }
}
