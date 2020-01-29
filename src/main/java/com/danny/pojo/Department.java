package com.danny.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Author 陈作钧
 * @Email chenzj36@live.cn
 * @Time 2020/1/14 13:29
 * @Description
 */
//这三个注解来自于lombok，用于生成构造函数，get，set，toString等常用的针对于类的方法
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Integer id;
    private String departmentName;
}
