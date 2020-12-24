package com.liu.effective.yml;

/**
 * @program: JVMDome
 * @description: 读取yml文件
 * @author: liuwenhao
 * @create: 2020-12-21 17:32
 **/
public class ReadYml {
    public static void main(String[] args) {
        System.out.println(YmlUtil.getResMap("datasource"));
    }
}