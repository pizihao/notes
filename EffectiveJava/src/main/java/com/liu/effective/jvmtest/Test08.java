package com.liu.effective.jvmtest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: JVMDome
 * @description: ThreadLocal
 * @author: liuwenhao
 * @create: 2020-12-03 17:25
 **/

public class Test08 {
    public static void main(String[] args) {
        ThreadLocal<String> local = new ThreadLocal<>();
        Test03 test03 = new Test03();
        IntStream.range(0,10).forEach(value -> {
            new Thread(() -> {
                local.set("和名字一样的随机值" + value);
                test03.sleepAll();
                System.out.println(value + "的local里面是:" + local.get());
                /*修改 */
                local.set("新的存储值" + (value + 1));
                test03.sleepAll();
                System.out.println(value + "的local里面是:" + local.get());
            }, "随机名字" + value).start();
        });
    }
}