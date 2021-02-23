package com.liu.bootweb.mapper;

import com.liu.bootweb.pojo.Department;
import com.liu.bootweb.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shidacaizi
 * @date 2020/4/18 15:01
 */
//员工Dao
@Repository
public class EmployeeMapper {
    // 模拟数据库中的数据
    private static Map<Integer, Employee> employeeMap = null;
    // 员工所属的部门
    @Autowired
    private DepartmentMapper departmentMapper;

    static {
        employeeMap = new HashMap<>();
        employeeMap.put(1001, new Employee(1001, "AA", "A132.@qq.com", 1, new Department(101, "侦查组")));
        employeeMap.put(1002, new Employee(1002, "AB", "B132.@qq.com", 0, new Department(101, "侦查组")));
        employeeMap.put(1003, new Employee(1003, "AC", "C132.@qq.com", 1, new Department(102, "航行指挥组")));
        employeeMap.put(1004, new Employee(1004, "AD", "D132.@qq.com", 0, new Department(103, "火力指挥组")));
        employeeMap.put(1005, new Employee(1005, "AE", "E132.@qq.com", 1, new Department(104, "后勤组")));
        employeeMap.put(1006, new Employee(1006, "AF", "F132.@qq.com", 0, new Department(104, "后勤组")));
        employeeMap.put(1007, new Employee(1007, "AG", "G132.@qq.com", 1, new Department(104, "后勤组")));
        employeeMap.put(1008, new Employee(1008, "AH", "H132.@qq.com", 1, new Department(104, "后勤组")));
        employeeMap.put(1009, new Employee(1009, "AI", "I132.@qq.com", 0, new Department(105, "火力组")));
        employeeMap.put(1010, new Employee(1010, "AJ", "J132.@qq.com", 1, new Department(105, "火力组")));
        employeeMap.put(1011, new Employee(1011, "AK", "K132.@qq.com", 0, new Department(105, "火力组")));
        employeeMap.put(1012, new Employee(1012, "AL", "L132.@qq.com", 1, new Department(105, "火力组")));
        employeeMap.put(1013, new Employee(1013, "AM", "M132.@qq.com", 0, new Department(105, "火力组")));
        employeeMap.put(1014, new Employee(1014, "AN", "N132.@qq.com", 0, new Department(105, "火力组")));
        employeeMap.put(1015, new Employee(1015, "AO", "O132.@qq.com", 1, new Department(105, "火力组")));
    }

    // 主键自增
    private static Integer initid = 1006;

    //增加一个员工
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initid++);
        }
        employee.setDepartment(departmentMapper.getDepartmentById(employee.getDepartment().getId()));

        employeeMap.put(employee.getId(), employee);
    }

    //查询全部员工信息
    public Collection<Employee> getAll() {
        return employeeMap.values();
    }

    // 查询一个
    public Employee getemployeeById(Integer id) {
        return employeeMap.get(id);
    }

    //删除一个
    public void delete(Integer id) {
        employeeMap.remove(id);
    }
}
