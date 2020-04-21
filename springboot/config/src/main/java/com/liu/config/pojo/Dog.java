package com.liu.config.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author shidacaizi
 * @date 2020/4/17 16:27
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog {
    private Integer id;
    @Value("宸宸")
    private String name;
    @Value("3")
    private Integer age;
}
