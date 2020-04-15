package com.liu.utils;

import org.junit.Test;

import java.util.UUID;

/**
 * @author shidacaizi
 * @date 2020/4/15 8:40
 */
public class IdUtils {

    public static String getId(){
        return UUID.randomUUID().toString().replace("-","");
    }

    @Test
    public void test(){
        System.out.println(getId());
    }
}
