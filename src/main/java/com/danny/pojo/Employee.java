package com.danny.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author 陈作钧
 * @Email chenzj36@live.cn
 * @Time 2020/1/15 12:12
 * @Description
 */
@Data
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;//0 female, 1 male
    private Department department;
    private Date birth;

    //自己写构造函数，birth直接new一个即可。
    public Employee(Integer id, String lastName, String email, Integer gender, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.birth = new Date();
    }
}
