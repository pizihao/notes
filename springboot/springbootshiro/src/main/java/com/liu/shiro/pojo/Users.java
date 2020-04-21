package com.liu.shiro.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shidacaizi
 * @date 2020/4/19 14:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private Integer id;
    private String name;
    private String pwd;
    private String perms;
}
