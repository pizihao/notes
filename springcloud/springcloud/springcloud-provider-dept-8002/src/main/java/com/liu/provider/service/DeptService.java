package com.liu.provider.service;

import com.liu.api.pojo.Dept;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/30 18:48
 */
public interface DeptService {

    boolean addDept(Dept dept);

    Dept queryById(Long id);

    List<Dept> queryAll();
}
