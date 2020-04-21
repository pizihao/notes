package com.liu.bootweb.controller;

import com.liu.bootweb.mapper.DepartmentMapper;
import com.liu.bootweb.mapper.EmployeeMapper;
import com.liu.bootweb.pojo.Department;
import com.liu.bootweb.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * @author shidacaizi
 * @date 2020/4/18 15:58
 */
@Controller
public class EmployeeConrtoller {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @RequestMapping("/emplist")
    public String list(Model model){
        Collection<Employee> employees = employeeMapper.getAll();
        model.addAttribute("employees", employees);
        return "/emp/list";
    }

    @GetMapping("/addemp")
    public String toaddemp(Model model){
        Collection<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }
    @PostMapping("/addemp")
    public String addemp(Employee employee){
        employeeMapper.save(employee);
        return "redirect:/emplist";
    }
}
