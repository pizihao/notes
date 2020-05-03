package com.liu.api.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author shidacaizi
 * @date 2020/4/30 13:54
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Dept implements Serializable {
    private Long deptno;
    private String dname;
    private String loc;

    //这个数据存在哪个数据的字段
    // 微服务一个服务对应一个数据库，
    // 同一个信息可能存在于不同的数据库中
    private String db_source;

    public Dept(String dname, String loc) {
        this.dname = dname;
        this.loc = loc;
    }
}
