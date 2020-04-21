package com.liu.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author shidacaizi
 * @date 2020/4/19 10:17
 */
@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    //查询数据库所有的信息
    //没有实体类，数据库中的东西使用map就可以了
    @GetMapping("/userlist")
    public List<Map<String, Object>> userList(){
        String sql = "select * from users";
        return jdbcTemplate.queryForList(sql);
    }
}
