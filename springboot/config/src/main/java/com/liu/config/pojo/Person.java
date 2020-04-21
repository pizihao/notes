package com.liu.config.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author shidacaizi
 * @date 2020/4/17 16:29
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private Integer age;
    private Boolean happy;
    private Date birth;
    private Map<String, Object> maps;
    private List<?> list;
    private Dog dog;
}
