[TOC]

# 项目简介

这是一个关于spring学习的项目

这是第一个的项目，所以目前不会有太多的内容，就是一个helloworld的项目而已

这里主要的是使用xml进行配置bean

简单的使用DI (dependence inject)

## spring IOC (控制反转、DI依赖注入)

**Ioc—Inversion of Control，即“控制反转”，不是什么技术，而是一种设计思想。**在Java开发中，**Ioc意味着将你设计好的对象交给容器控制，而不是传统的在你的对象内部直接控制。**如何理解好Ioc呢？理解好Ioc的关键是要明确“谁控制谁，控制什么，为何是反转（有反转就应该有正转了），哪些方面反转了”，那我们来深入分析一下：

　　●**谁控制谁，控制什么**：传统Java SE程序设计，我们直接在对象内部通过new进行创建对象，是程序主动去创建依赖对象；而IoC是有专门一个容器来创建这些对象，即由Ioc容器来控制对 象的创建；**谁控制谁？当然是IoC 容器控制了对象；控制什么？那就是主要控制了外部资源获取（不只是对象包括比如文件等）。**

　　●**为何是反转，哪些方面反转了**：有反转就有正转，传统应用程序是由我们自己在对象中主动控制去直接获取依赖对象，也就是正转；而反转则是由容器来帮忙创建及注入依赖对象；为何是反转？**因为由容器帮我们查找及注入依赖对象，对象只是被动的接受依赖对象，所以是反转；哪些方面反转了？依赖对象的获取被反转了。**

![img](http://images.cnitblog.com/blog/289233/201501/261421378318292.jpg) 

以上是自己手动的进行依赖，也就是正常的手动创建依赖

IOC也就是由容器进行对象的管理和创建，当我们需要依赖的时候，自动的为我们注入依赖，不需要我们手动new的方式进行依赖创建，这就是控制权的反转，依赖的控制权交给了IOC容器

## 几种IOC注入的方式

### 构造注入

constructor进行注入

也就是通过有参构造方法，在创建对象的时候传入依赖类的对象

用spring xml的方式如下



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="userDao" class="com.demo01.dao.UserDaoImpl">
	</bean>
	
	<!--使用构造器的方式进行依赖注入-->
	<bean id="userService" class="com.demo01.service.UserServiceImpl">
        <!--这里的参数 userDao 用上面的 id为userDao的对象进行注入 ref指的是引用的意思-->
		<constructor-arg name="userDao" ref="userDao"></constructor-arg>
	</bean>
	
	
</beans>
```



注意：<bean></bean>标签里的id为唯一标识符，虽然可以有id相同的，但是不建议这么使用

<constructor-arg name="userDao" ref="userdao"></constructor-arg>

constructor-arg 标签里面的 **name** 代表的是**函数的参数**名称

**ref** 指的是用这里xml的管理bean 来进行注入

所以这里的ref的值为上面管理的id为userDao的对象

构造方法参数传入的对象是 id为userDao的对象

这里必须要注意的是

<constructor-arg></constructor-arg>

**构造函数有多少个参数就要有多少个constructor-arg 标签**

不然会报错

也就是要想用有几个参数的构造函数，就必须有几个constructor-arg

### set注入

顾名思义，就是用setter方法进行注入依赖

所以**必须要有set方法**，不然会报错

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean id="userDao" class="com.demo01.dao.UserDaoImpl">
	</bean>
	
	<bean id="userService" class="com.demo01.service.UserServiceImpl">
        <!--找的set方法为 setUserDao 通过这个方法进行依赖注入-->
		<property name="userDao">
            <!--ref 引用上面的 id为userDao的类-->
			<ref bean="userDao"/>
		</property>
	</bean>
	
</beans>
```



以上就是用set方法注入的示例



## 使用IOC容器

使用了以上的配置文件后,要使其生效，所以要用IOC容器加载xml文件，这样才能使依赖注入生效

```java
ApplicationContext context=new ClassPathXmlApplicationContext("application.xml");
//这个是通过在类路径的跟目录下进行加载xml文件
//通常这个路径就是在src的目录下
```

通过以上的语句就能使xml生效，spring也会开始工作，进行管理我们的IOC容器，管理注册的bean对象，注入依赖

### 获取管理的bean对象

```java
UserService userService = (UserService)context.getBean("userService");
//通过这条语句，spring会自动的查找 id为：userService的类
//这是通过id来找对象
//当然也可以通过类型进行找对象
UserService userService = context.getBean(UserService.class);
//这里就不需要强制转换了，因为类型都传入进去了，spring已经知道你要的类型，所以可以自动的查找到你要的对象
```



通过获取spring的管理的bean对象，就会获取到有注入依赖的对象

spring 管理的对象默认是单利模式，所以可以尽可能的为我们管理好内存，不会造成多余对象的内存浪费

