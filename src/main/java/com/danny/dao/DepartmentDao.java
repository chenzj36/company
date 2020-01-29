package com.danny.dao;

import com.danny.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Danny Lyons
 * @Email chenzj36@live.cn
 * @Time 2020/1/15 12:16
 * @Description
 */
//交由SpringBoot管理，加了@Repository就可以使用@AutoWired
@Repository
public class DepartmentDao {
    //模拟数据库中的数据，Map<整型索引，类>，类型为static
    private static Map<Integer, Department> departments = null;
    static {
        // 静态代码块，用到类的时候会先行加载（仅一次），起到为“数据库”添加数据的作用。
        departments = new HashMap<>();//创建一个部门表
        // 向Map中添加数据
        departments.put(101, new Department(101,"教学部"));
        departments.put(102, new Department(102,"科研部"));
        departments.put(103, new Department(103,"市场部"));
        departments.put(104, new Department(104,"运营部"));
        departments.put(105, new Department(105,"后勤部"));
    }
    //获得所有部门信息
    public Collection<Department> getDepartments(){
        return departments.values();
    }

    //通过id得到部门
    public Department getDepartmentById(Integer id){
        return departments.get(id);
    }
}
