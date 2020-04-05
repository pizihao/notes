package cn.liu.softtest._05;

import cn.hutool.core.date.DateException;
import cn.hutool.core.date.DateUtil;

import java.util.Scanner;

/**
 * @author shidacaizi
 * @date 2020/4/4 9:43
 */
public class Week {

    public void weekes(Scanner in){
        System.out.println("请输入年，月，日。。。如2010-01-01");
        try {
            String week = in.next();
            System.out.println(DateUtil.dayOfWeekEnum(DateUtil.offsetDay(DateUtil.parse(week), 2)).toChinese());
        } catch (DateException e) {
            System.out.println("日期格式不正确");
            weekes(in);
        }
    }
}
