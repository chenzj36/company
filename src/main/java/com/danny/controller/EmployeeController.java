package com.danny.controller;

import com.danny.dao.DepartmentDao;
import com.danny.dao.EmployeeDao;
import com.danny.pojo.Department;
import com.danny.pojo.Employee;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * @Author Danny Lyons
 * @Email chenzj36@live.cn
 * @Time 2020/1/22 10:29
 * @Description 员工管理页面controller
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/list.html")
    public String list(Model model){
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps", all);
        return "/emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "/emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("==>"+employee);
        employeeDao.save(employee);
        return "redirect:list.html";
    }

    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp", employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        System.out.println("update==>"+employee);
        employeeDao.save(employee); // 同一id，用save代替update完成相应的操作。
        return "redirect:/list.html"; // 重定向到员工列表显示页
    }

    @GetMapping("/delEmp/{id}")
    public String delEmp(@PathVariable("id") Integer id){
        employeeDao.dele(id);
        return "redirect:/list.html";
    }

    @GetMapping("/user/loginOut")
    public String signOut(HttpSession session){
        session.invalidate();
        return "redirect:/main.html";
    }
}
