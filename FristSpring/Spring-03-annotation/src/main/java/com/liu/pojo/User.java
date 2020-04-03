package com.liu.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author shidacaizi
 * @date 2020/4/2 12:43
 */
// 组件
@Component
public class User {
    public String name;

    @Value("十大才子")
    public void setName(String name) {
        this.name = name;
    }
}
