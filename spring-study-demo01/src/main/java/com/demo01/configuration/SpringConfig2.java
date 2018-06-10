package com.demo01.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//这里的basePackages 可以写多个包 这是数组的写法
@ComponentScan(basePackages= {"com.demo01.**"})
public class SpringConfig2 {

}
