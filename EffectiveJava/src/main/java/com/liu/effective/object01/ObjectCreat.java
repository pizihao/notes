package com.liu.effective.object01;

import lombok.Builder;

/**
 * @program: JVMDome
 * @description: 创建和销毁对象
 * @author: liuwenhao
 * @create: 2020-11-13 15:51
 **/
@Builder(toBuilder = true)
public class ObjectCreat {



    public static ObjectCreat returnChild(){
        return new ObjectCreat01();
    }
}