package com.liu.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author shidacaizi
 * @date 2020/4/21 8:30
 */
@Service
public class ScheduledService {
    //在一个特定的时间执行这个方法
    //依次是秒 分 时 日 月 周几
    //有关cron表达式
    @Scheduled(cron = "30 * * * * ?")
    public void hello(){
        System.out.println("hello,你被执行了");
    }
}
