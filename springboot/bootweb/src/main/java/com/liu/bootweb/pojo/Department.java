package com.liu.bootweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author shidacaizi
 * @date 2020/4/18 14:44
 */
// 部门表
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Department {
    private Integer id;
    private String departmentname;
}
