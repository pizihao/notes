package com.liu.bootweb.mapper;

import com.liu.bootweb.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shidacaizi
 * @date 2020/4/18 14:47
 */
//部门Dao
@Repository
public class DepartmentMapper {
    // 模拟数据库中的数据

    private static Map<Integer, Department> departmentMap = null;

    static {
        departmentMap = new HashMap<>();
        departmentMap.put(101, new Department(101, "侦查组"));
        departmentMap.put(102, new Department(102, "航行指挥组"));
        departmentMap.put(103, new Department(103, "火力指挥组"));
        departmentMap.put(104, new Department(104, "后勤组"));
        departmentMap.put(105, new Department(105, "火力组"));
//        departmentMap.put(106,new Department(106,"投放组"));
//        departmentMap.put(107,new Department(107,"兵力调遣组"));
//        departmentMap.put(108,new Department(108,"右翼组"));
//        departmentMap.put(109,new Department(109,"左翼组"));
//        departmentMap.put(110,new Department(110,"突击组"));
//        departmentMap.put(111,new Department(111,"防守组"));
//        departmentMap.put(112,new Department(112,"拦截组"));
//        departmentMap.put(113,new Department(113,"补给组"));
//        departmentMap.put(114,new Department(114,"隐形轰炸组"));
//        departmentMap.put(115,new Department(115,"战略部署组"));
//        departmentMap.put(116,new Department(116,"战略斩首组"));
    }

    // 获取所有部门信息
    public Collection<Department> getDepartments() {
        return departmentMap.values();
    }

    //通过ID得到部门
    public Department getDepartmentById(Integer id) {
        return departmentMap.get(id);
    }
}
