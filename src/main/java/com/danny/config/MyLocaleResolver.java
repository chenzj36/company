package com.danny.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Author Danny Lyons
 * @Email chenzj36@live.cn
 * @Time 2020/1/20 20:54
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
