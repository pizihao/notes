package com.liu.provider.mapper;

import com.liu.api.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shidacaizi
 * @date 2020/4/30 14:46
 */
@Mapper
@Repository
public interface DeptMapper {

    boolean addDept(Dept dept);

    Dept queryById(@Param("deptno") Long id);

    List<Dept> queryAll();

}
