package com.liu.everyGC;

import java.util.Random;

/**
 * @author shidacaizi
 * @date 2020/3/18 14:07
 */
/*
*
* 1，-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC   (DefNew+Tenured)
* 2，-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC   (ParNew+Tenured)
* 备注情况: Java Hotspot(TM) 64- Bit Server VM warning:
* Using the ParNew young collector with the Serial old collector is deprecated
* and will likely be removed in a future release
* 3，-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC    (PSYoungGen+ParoldGen)
* 4.1，-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC    (PSYoungGen+ParoldGen)
* 4.2，不加就是默认UseParallelGC
* -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags    (PSYoungGen+ParoldGen)
* 5，-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC
* 6，-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
* 7 (理论知道即可，实际中已经被优化鹅了，没有了。)
* -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+printCommandLineFlags -XX:+UseSerialoldGC
* */
public class everyGCDemo01 {
    public static void main(String[] args) throws InterruptedException {
        try {
            String str = "liuwenhao";
            while (true){
                str += str + new Random().nextInt(11111111) + new Random().nextInt(22222222);
                str.intern();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
