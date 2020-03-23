package com.puls;

import java.util.ArrayList;

/**
 * @author shidacaizi
 * @data 2020/3/10 21:29
 */
// -Xms8m -Xmx8m -XX:+PrintGCDetails
// -Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError

     //-Xms 设置初始化内存分配大小
public class JvmTest {
     byte[] array = new byte[1*1024*1024]; //1M

     public static void main(String[] args) {
          ArrayList<JvmTest> list = new ArrayList<>();
          int count = 0;

          try {
               while (true){
                    list.add(new JvmTest());
                    count = count++;
               }
          } catch (Error e) {
               System.out.println("count:"+count);
               e.printStackTrace();
          }
     }
}
