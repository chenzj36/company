**完整项目（简单的“员工管理系统CRUD”）地址：** https://github.com/chenzj36/company
**本项目系西部开源狂神SpringBoot教程学习笔记（下附原文链接）**

*   https://blog.kuangstudy.com/index.php/archives/630/
*   https://www.cnblogs.com/hellokuangshen/tag/SpringBoot/

# 预备知识  
## 简介  
### 核心思想  

*   **约定大于配置**  

### 主要优点  
*   快速入门
*   开箱即用
*   内嵌式容器
*   支持yaml配置

### 理解微服务架构  
*   英文原文：http://martinfowler.com/articles/microservices.html
*   中文版本：https://www.cnblogs.com/liuning8023/p/4493156.html

### 一个最简单的应用  
1.  项目结构
    ![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580528260993.png)
2.  HelloController.class
    ![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580528281240.png)
3.  运行效果
   ![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580528293649.png)
4.  项目打包成jar包：双击package即可
    ![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580528311565.png)
5.  运行jar包
    java -jar jar包名

### 自定义启动banner 
*   resources目录下新建banner.txt
*   banner图形：https://www.bootschool.net/ascii

### 再举一controller的例子  
![enter description here](http://q5053ip41.bkt.clouddn.com/xsj/1580528337146.png)
![enter description here](http://q5053ip41.bkt.clouddn.com/xsj/1580528349740.png)

### 涉及到实体类的一个项目（yaml）  
![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580528337146.png)
![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580528349740.png)

### 涉及到实体类的一个项目（yaml）  
1.  项目结构
    ![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580528364444.png)
2.  pojo

*   Dog.class
    ![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580528383563.png)
*   Person.class
    ![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580528399362.png)

1.  yaml
    ![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580528417905.png)
2.  test
    ![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580528435499.png)
3.  运行结果（控制台打印输出）
    `Person{name='chenzj', age=23, happy=false, birth=Thu Jul 03 00:00:00 CST 1997, maps={k1=v1, k2=v2}, lists=[code, girl, music], dog=Dog{name='wangcai', age=1}}`
4.  多环境切换（yml中配置）
    ![enter description here](https://aliyunosschenzj.oss-cn-beijing.aliyuncs.com/aliyunoss/1580528452643.png)

### 其余需要了解的知识  
*   松散绑定lastName   last-name
*   JSR303数据校验@Validated
*   图标自定义：resources/public/favicon.ico
*   静态资源映射：/public，/resources，/static直接访问；/templates需用controller映射（模板引擎）

### 使用SpringBoot的步骤  
1.  创建一个SpringBoot应用
2.  选择所需的模块
3.  在配置文件中手动配置部分配置项目
4.  编写业务代码

### 类的命名  
*   向容器中自动配置组件：___AutoConfiguration
*   自动配置类：____Properties

### 模板引擎  

*   作用：模板引擎的作用就是我们来写一个页面模板，比如有些值呢，是动态的，我们写一些表达式。而这些值，从哪来呢，我们来组装一些数据，我们把这些数据找到。然后把这个模板和这个数据交给我们模板引擎，模板引擎按照我们这个数据帮你把这表达式解析、填充到我们指定的位置，然后把这个数据最终生成一个我们想要的内容给我们写出去
*   常用：freemarker Thymeleaf js
*   Thymeleaf官网：https://www.thymeleaf.org/

# 员工管理系统  

## 准备工作  

### 添加依赖  

```
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

```

### pojo包(Plain Ordinary Java Object)普通JavaBeans

*   Department.class

```
package com.danny.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//这三个注解来自于lombok，用于生成构造函数，get，set，toString等常用的针对于类的方法
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Integer id;
    private String departmentName;
}

```

*   Employee.class

```
package com.danny.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

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

```

### dao包(Data Access Object)数据访问接口

*   DepartmentDao.class

```
package com.danny.dao;

import com.danny.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository // 让Spring托管
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

```

*   EmployeeDao.class

```
package com.danny.dao;

import com.danny.pojo.Department;
import com.danny.pojo.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    //模拟一个数据库
    private static Map<Integer, Employee> employees = null;
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

    //增加一个员工
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

```

### 更改项目启动名

`server.servlet.context-path=/chen`

## 登录功能实现

*   MyMvcConfig.class自定义视图解析

```
package com.danny.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Danny Lyons
 * @Email chenzj36@live.cn
 * @Time 2020/1/20 13:28
 * @Description 自定义MvcConfig类，实现视图自定义
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index"); // 将url解析到name对应的/templates/name.html
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

   @Bean  //自定义的国际化解析器，放到一个Bean中使其生效
   public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
   }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login","/img/**","/js/**","/css/**","/static/**");
    }
}

```

*   index.html表单

```
<form class="form-signin" th:action="@{/user/login}">
	<img class="mb-4" th:src="@{/img/bootstrap-solid.svg}" alt="" width="72" height="72">
	<h1 class="h3 mb-3 font-weight-normal" th:text="#{login.tip}">Please sign in</h1>
	<p th:text="${msg}" style="color: red" th:if="${not #strings.isEmpty(msg)}"></p>
	<label class="sr-only">Username</label>
	<input type="text" name="username" class="form-control" th:placeholder="#{login.username}" required="" autofocus="">
	<label class="sr-only">Password</label>
	<input type="password" name="password" class="form-control" th:placeholder="#{login.password}" required="">
	<div class="checkbox mb-3">
		<label>
  <input type="checkbox" >[[#{login.remenber}]]
</label>
	</div>
	<button class="btn btn-lg btn-primary btn-block" type="submit">[[#{login.btn}]]</button>
	<p class="mt-5 mb-3 text-muted">© 2019-2020</p>
	<a class="btn btn-sm" th:href="@{/index.html(l='zh_CN')}">中文</a>
	<a class="btn btn-sm" th:href="@{/index.html(l='en_US')}">English</a>
</form>

```

*   LoginController.class

```
package com.danny.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password")String password, Model model, HttpSession session){
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","用户名或者密码错误");
            return "index";
        }
    }
}

```

## 页面国际化

### 项目所需文件

#### /resources/i18n/

> 
> 
> login.properties
> login_zh_CN.properties
> login_en_US.properties
> 
> 

```
login.btn=登录
login.password=密码
login.remenber=记住我
login.tip=请登录
login.username=用户名

```

*   html文件置于templates文件夹下
*   css、img、js文件置于static文件夹下

### IDEA设置properties的编码

*   Setting-->Editor-->File Encoding编码同一修改为UTF-8

### 配置application.properties

`spring.messages.basename=i18n.login`

### 配置index.html

```
<!--括号里的即为HttpServletRequest-->
<a th: >中文</a>
<a th: >English</a>

```

### config包下的类

*   MyLocaleResolver.class自定义国际化消息视图解析

```
package com.danny.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Description 自定义国际化解析类，实现LocaleResolver接口
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {

        //获取请求的参数
        String language = httpServletRequest.getParameter("l");

        //如果请求的链接携带了国际化的参数，进行判断，取参数
        //用默认值赋值
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(language)){ // 用StringUtils工具类进行判空
            String[] s = language.split("_"); //分割参数
            locale = new Locale(s[0], s[1]); // 语言_地区
        }
        return locale; // 返回locale
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
        // 返回为空，没有必要重写
    }
}

```

*   MyMvcConfig.class

```
package com.danny.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description 自定义MvcConfig类，实现视图自定义
 */

//注解：表明是配置类
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index"); // 将url解析到name对应的/templates/name.html
        registry.addViewController("/index.html").setViewName("index");
    }

   @Bean  //自定义的国际化解析器，放到一个Bean中使其生效，检测的时候this.LocaleResolver!=null
   public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
   }
}

```

## 登录功能实现

### 在index.html文件中添加

*   在提示下添加`<p th:text="${msg}" style="color: red" th:if="${not #strings.isEmpty(msg)}"></p>`
*   在form标签中添加`th:action="@{/user/login}"`
*   在input标签中添加name属性`name="username"` `name="password"`

### controler包

*   LoginController.class

```
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password")String password, Model model, HttpSession session){
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","用户名或者密码错误");
            return "index";
        }
    }
}

```

### config包

*   MyMvcConfig.class
    添加`registry.addViewController("/main.html").setViewName("dashboard");`

## 拦截器

### config包

*   LoginHandlerInterceptor.class

```
package com.danny.config;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null){
            //没有登录
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }else {
            return true;
        }
    }
}

```

*   LoginController.class 添加`session.setAttribute("loginUser", username);`HttpSession作为方法的参数即可
*   在MyMvcConfig.class中添加代码,注册拦截器

```
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginHandlerInterceptor())
            .addPathPatterns("/**")
            .excludePathPatterns("/index.html","/","/user/login","/img/**","/js/**","/css/**");
}

```

dashboard.html中添加`[[${session.loginUser}]]`替换MyCompany，显示登录用户名。

## 展示员工列表

### list.html修改列表显示部分

```
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
	<h2>员工列表</h2>
	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<thead>
				<tr>
					<th>id</th>
					<th>lastName</th>
					<th>email</th>
					<th>gender</th>
					<th>department</th>
					<th>birth</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr th:each="emp:${emps}">
					<td th:text="${emp.getId()}"></td>
					<td th:text="${emp.getLastName()}"></td>
					<td th:text="${emp.getEmail()}"></td>
					<td th:text="${emp.getGender()==0?'女':'男'}"></td>
					<td th:text="${emp.getDepartment().getDepartmentName()}"></td>
					<td th:text="${#dates.format(emp.getBirth(),'YYYY-MM-DD HH:mm:ss')}"></td>
					<td>
						<button class="btn btn-sm btn-primary">编辑</button>
						<button class="btn btn-sm btn-danger">删除</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</main>

```

### EmployeeController.class

```
package com.danny.controller;

import com.danny.dao.EmployeeDao;
import com.danny.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Collection;
@Controller
public class EmployeeController {

@Autowired
EmployeeDao employeeDao;

@RequestMapping("/list.html")
public String list(Model model){//model携带参数给前端
    Collection<Employee> all = employeeDao.getAll();
    model.addAttribute("emps", all);
    return "list";
}
}

```

### 侧边栏对应员工管理部分

实现选中的部分高亮
`<a th:th: >`

## 添加员工信息

*   添加员工按钮超链接list.html

```
<h2><a class="btn btn-sm btn-success" th:href="@{/emp}">添加员工</a></h2>

```

*   对应controller EmployeeController.class

```
@GetMapping("/emp")
public String toAddPage(Model model){
    Collection<Department> departments = departmentDao.getDepartments();
    model.addAttribute("departments",departments);
    return "/emp/add";
}

```

*   添加员工信息页面 add.html(表单部分)

```
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
	<h2>添加员工</h2>
	<div class="form-check">
		<form th:action="@{/emp}" method="post" >
			<div class="form-group">
				<label>LastName</label>
				<input type="text" name="lastName" class="form-control" placeholder="kuangshen">
			</div>
			<div class="form-group">
				<label>Email</label>
				<input type="email" name="email" class="form-control" placeholder="24736743@qq.com">
			</div>
			<div class="form-group">
				<label>Gender</label><br/>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="gender"  value="1">
					<label class="form-check-label">男</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="gender"  value="0">
					<label class="form-check-label">女</label>
				</div>
			</div>
			<div class="form-group">
				<label>department</label>
				<select class="form-control" name="department.id">
					<option name="department" th:each="dept:${departments}" th:text="${dept.getDepartmentName()}" th:value="${dept.getId()}">1</option>
				</select>
			</div>
			<div class="form-group">
				<label>Birth</label>
				<input type="text" name="birth" class="form-control" placeholder="kuangstudy">
			</div>
			<button type="submit" class="btn btn-primary">添加</button>
		</form>

	</div>
</main>

```

*   提交表单后的controller

```
@PostMapping("/emp")
public String addEmp(Employee employee){
    System.out.println("==>"+employee);
    employeeDao.save(employee);
    return "redirect:list.html";
}

```

*   修改配置

```
# 时间日期格式化
spring.mvc.date-format=yyyy-mm-dd

```

## 修改员工信息功能

### 跳转链接设置

`<a th: >编辑</a>`

### 跳转controller

```
@GetMapping("/emp/{id}")
public String toUpdateEmp(@PathVariable("id") Integer id, Model model){
    Employee employee = employeeDao.getEmployeeById(id);
    model.addAttribute("emp", employee);
    Collection<Department> departments = departmentDao.getDepartments();
    model.addAttribute("departments", departments);
    return "emp/update";
}

```

### 修改员工信息页面update.html-复制list.html

```
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
	<h2>修改员工信息</h2>
	<div class="form-check">
		<form th:action="@{/updateEmp}" method="post" >
			<div class="form-group">
				<input type="hidden" name="id" class="form-control" th:value="${emp.id}">
			</div>
			<div class="form-group">
				<label>LastName</label>
				<input type="text" name="lastName" class="form-control" th:value="${emp.getLastName()}">
			</div>
			<div class="form-group">
				<label>Email</label>
				<input type="email" name="email" class="form-control" th:value="${emp.getEmail()}">
			</div>
			<div class="form-group">
				<label>Gender</label><br/>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="gender"  value="1" th:checked="${emp.getGender()==1}">
					<label class="form-check-label">男</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="gender"  value="0" th:checked="${emp.getGender()==0}">
					<label class="form-check-label">女</label>
				</div>
			</div>
			<div class="form-group">
				<label>department</label>
				<select class="form-control" name="department.id">
					<option name="department" th:each="dept:${departments}" th:selected="${dept.id==emp.department.id}"
							th:text="${dept.getDepartmentName()}" th:value="${dept.getId()}">1</option>
				</select>
			</div>
			<div class="form-group">
				<label>Birth</label>
				<input name="birth" type="text" class="form-control" th:value="${#dates.format(emp.birth,'yyyy-MM-dd HH:mm')}">
			</div>
			<button type="submit" class="btn btn-primary">确认修改</button>
		</form>
	</div>
</main>

```

### 提交控制controller

```
@PostMapping("/updateEmp")
public String updateEmp(Employee employee){
    System.out.println("update==>"+employee);
    employeeDao.save(employee);
    return "redirect:/list.html";
}

```

## 删除员工

### 编写提交地址

`<a th: >删除</a>`

### controller

```
@GetMapping("/delEmp/{id}")
public String delEmp(@PathVariable("id") Integer id){
    employeeDao.dele(id);
    return "redirect:/list.html";
}

```

## 注销功能

### 链接设置

`<a th: >Sign out</a>`

### controller

```
@GetMapping("/user/loginOut")
public String signOut(HttpSession session){
    session.invalidate();
    return "redirect:/main.html";
}
