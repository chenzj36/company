package com.danny.dao;

import com.danny.pojo.Department;
import com.danny.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Danny Lyons
 * @Email chenzj36@live.cn
 * @Time 2020/1/15 12:36
 * @Description
 */
@Repository
public class EmployeeDao {
    //模拟一个数据库
    private static Map<Integer, Employee> employees = null;
    @Autowired
    private DepartmentDao departmentDao;
    static{
        employees = new HashMap<>();
        employees.put(1001, new Employee(1001, "AA", "Achenzj36@live.cn", 1, new Department(101,"教学部")));
        employees.put(1002, new Employee(1002, "BB", "Bchenzj36@live.cn", 0, new Department(102,"科研部")));
        employees.put(1003, new Employee(1003, "CC", "Cchenzj36@live.cn", 1, new Department(103,"市场部")));
        employees.put(1004, new Employee(1004, "DD", "Dchenzj36@live.cn", 0, new Department(104,"运营部")));
        employees.put(1005, new Employee(1005, "EE", "Echenzj36@live.cn", 1, new Department(105,"后勤部")));
    }

    //主键自增
    private static Integer initid = 1006;

    //增加(修改)一个员工
    public void save(Employee employee) {
        if(null == employee.getId()){
            employee.setId(initid++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    //查询全部员工信息
    public Collection<Employee> getAll(){
        return employees.values();
    }

    //通过id查询员工信息
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    //删除一个员工
    public void dele(Integer id){
        employees.remove(id);
    }

}
