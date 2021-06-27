package com.liu.excel;

import java.util.StringJoiner;

/**
 * @program: JVMDome
 * @description: StringJoiner
 * @author: liuwenhao
 * @create: 2021-02-23 17:20
 **/
public class strJoinerTest {
    public static void main(String[] args) {
        StringJoiner stringJoiner = new StringJoiner(":","","");
        stringJoiner.add("liu").add("wen").add("hao").add(stringJoiner.toString());
        System.out.println(stringJoiner);
    }
}