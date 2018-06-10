package com.demo01.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
//这里的basePackages 可以写多个包 这是数组的写法
@ComponentScan(basePackages= {"com.demo01.**"})
//导入java配置类
@Import(SpringConfig2.class)
//导入xml文件
@ImportResource({"a.xml","b.xml"})
public class SpringConfig {

}
