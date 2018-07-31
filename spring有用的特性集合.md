[TOC]



# Spring  Frame work 有用的特性

此文档主要用来介绍一些有用的spring的特性，以便以后的扩展，这里只做简单的介绍，详细的请根据自己的需求进行百度搜索。

## Spring 的一些坑

首先 **扫描本级包以及下面的所有子包**的写法是 **..*** 而不是 .**

自动扫描包 basePackage =""

只要写到某个包就好了,自动扫描本级及以下的所有包也就是不用写.* 也不用写.** ..*之类的 直接写到包名就好了

## IOC、DI(控制反转 依赖注入)

spring in action书上第 p27

### 应用场景

在各种有依赖类中使用、

例如

```java
class A{
    B b;
}
//B是一个自定义的对象
//这里A依赖B，也就是A需要调用B中的方法才能完成其自身的功能
//传统的做法是b= new B(); 进行手动的依赖配置
//通过IOC 我们可以让 Spring 自动为我们创建B对象，并且赋值给b
```

### 作用

解耦合，在以后要修改B实现类对象的源码时，可能我们会更改B实现类的名字

例如以前的B实现类的名字为BImpl

要进行迭代更新的时候 可能我们的命名就变成了BImpl1、BImpl2 ...

如果变了的话，我们是不是又要找到代码进行修改b=new BImpl1();这样的话在很多地方都需要改动，耦合度太高

所以交给spring 就可以改变这种耦合的问题，不要的时候，换一下注册的bean为BImpl2就可以继续使用了

## 关于Spring IOC的使用心得

### 解决应用开启多个的多例模式问题

在设计应用的时候要尽量的使用单例模式,spring**默认的模式**就是**单例模式**,我们不要轻易的去修改它,因为这个单例模式是来解决耦合性的问题的

那么问题来了,万一我的应用要开启多个怎么办，那么这就很简单了，**我们开两个spring容器不就解决了这个问题**了吗。这样就是一个双例模式了

### 怎么开两个spring容器?

```java
Applicationcontext ct1=new AnnotationConfigApplicationContext(StudentSpringConfiguration.class);

Applicationcontext ct2=new AnnotationConfigApplicationContext(StudentSpringConfiguration.class);
```





就这样开启了两个容器

所以其实Spring的设计都是十分合理的，我们不要轻易的修改默认值。



## AOP(面向切面编程)

spring in action书上第 p37

### 应用场景

应用于各种模块化的代码，例如权限控制，日记记录等等

例如我们只要写一个简单的功能，例如登录功能，在登录之前我们要进行日记记录

登录之后又要进行记录日记

这样我们的核心代码就被包围到很多代码之中，不好进行查阅，所以我们要让多余的代码独立开来

AOP就是作用于方法的一种简化方法复杂度的编程模式，当符合切入的条件时，可以在方法之前，方法执行之后，返回后，异常时进行相应的处理。AOP的代码不是写在类的方法上的，而是写在另一个类中，一点都不改变本方法的任何代码行。spring的非侵入性让我们的实体类保持整洁。

### 作用

保持方法的简洁，高效，没有多余的代码。

## 使用模板消除板式代码JdbcTemplate 

### 应用场景

在jdbc中，我们经常要用到大量的代码进行管理连接(connection)、管理会话(statement)、管理结果集(resultset)...核心代码就只有几行，但是一大堆附带的代码却很多，不利于查看核心业务的编写

![传统jdbc](img\传统jdbc.png)

使用模板如下

![使用jdbc模板](img\使用jdbc模板.png)

多余的代码都不见了

### 作用

简化各种多余的代码

## Spring 不同的上下文(context)使用

spring in action p44

- AnnotationConfigApplicationContext： 从一个或多个基于Java的配置类中加载Spring应用上下文。
- AnnotationConfigWebApplicationContext： 从一个或多个基于Java的配置类中加载Spring Web应用上下文。
- ClassPathXmlApplicationContext： 从类路径下的一个或多个XML配置文件中加载上下文定义， 把应用上下文的定义文件作为类资源。
- FileSystemXmlapplicationcontext： 从文件系统下的一个或多个XML配置文件中加载上下文定义。
- XmlWebApplicationContext： 从Web应用下的一个或多个XML配置文件中加载上下文定义



## bean 在spring的生命周期(面试可能要问)

spring in action p45

1. Spring对bean进行实例化；
2. Spring将值和bean的引用注入到bean对应的属性中；
3. 如果bean实现了BeanNameAware接口， Spring将bean的ID传递给setBean-Name()方法；

4. 如果bean实现了BeanFactoryAware接口， Spring将调用setBeanFactory()方法， 将BeanFactory容器实例传入；

5. 如果bean实现了ApplicationContextAware接口， Spring将调用setApplicationContext()方法， 将bean所在的应用上下文的引用传入进来；

6. 如果bean实现了BeanPostProcessor接口， Spring将调用它们的post-ProcessBeforeInitialization()方法；

7. 如果bean实现了InitializingBean接口， Spring将调用它们的after-PropertiesSet()方法。 类似地， 如果bean使用initmethod声明了初始化方法， 该方法也会被调用；

8.  如果bean实现了BeanPostProcessor接口， Spring将调用它们的post-ProcessAfterInitialization()方法；

9. 此时， bean已经准备就绪， 可以被应用程序使用了， 它们将一直驻留在应用上下文中， 直到该应用上下文被销毁；

10. 如果bean实现了DisposableBean接口， Spring将调用它的destroy()接口方法。 同样， 如果bean使用destroy-method声明了销毁方法， 该方法也会被调用

![bean的生命周期](img\bean的生命周期.png)

## Spring 开启组件扫描

spring in action p67

### 使用场景

当我们不想自己手动的在xml中写bean标签，进行对类的注册，或者在javaconfig中注册bean的时候,我们可以使用componentScan进行组件扫描，进行组件扫描后，就会自动在容器中组成bean

xml中开启扫描组件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
	<!-- 开启注解扫描 -->
	<context:annotation-config />
	<!-- 开启组件扫描 -->
	<!-- 开启组件扫描 默认开启注解扫描 -->
	<!-- base-package 扫描的包名, 有多个包的时候 ,可以使用 , ; 和空格进行隔开 spring都支持 -->
	<!-- .**代表不知道有多少层包 就是com.demo01和它的下层包都进行扫描 -->
	<context:component-scan base-package="com.demo01.**"></context:component-scan>
</beans>
```

#### 扫描多个包的时候的写法

spring官方文档5.02 中文版 p81

![basepackage的写法](img\basepackage的写法.png)



javaconfig中开启组件扫描

```java
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//这里的basePackages 可以写多个包 这是数组的写法
@ComponentScan(basePackages= {"com.demo01.**"})
public class SpringConfig {

}
```

**当我们使用@ComponentScan注解时，没有配置basePackages的时候，默认扫描配置类的包和及其下级的所有包**

spring in action p64

![componentScan默认的包](img\componentScan默认的包.png)





## Spring 导入配置文件或者导入配置类

Spring in action p92

### 使用场景

​	java是一个进行大项目开发的语言，因此团队开发非常重要，有些时候我们要进行模块划分，一个人负责开发一部分的模块，通常情况下，我们应该是一个模块进行一个单独的配置，也就是一个模块一个xml或者javaconfig类。

​	那么问题来了，如何把所有的xml都起到作用呢？难道我们是用

```java
new ClassPathXmlApplicationContext("1.xml","2.xml","3.xml"...);
```

来使所有的xml启作用吗？这是不可能的。

正确的做法应该是导入配置文件

### 例子

在xml中导入xml，导入javacongfig的方法

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	<!-- 导入同级目录下的a.xml -->
	<import resource="a.xml"/>
	<!-- 导入javaconfig -->
	<!-- 只要声明一个类就能导入javaconfig -->
	<bean class="com.demo01.configur"></bean>
	
</beans>
```

在javaconfig中导入xml

```java
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
```

## Profile根据不同的环境,配置不同的类

### 使用场景

我们在开发的过程中，会做很多的测试，会输出各种调试信息

但在生产环境的时候，已经经过了稳定性测试，不会再输出各种测试信息，所以因此我们要把多余的操作都给去掉，提高运行速度。也就是运行模式从开发的dev 改为 生产的prod 模式

**Spring** 提供了一套机制用于让我们根据不同的环境切换成不同的类，那就是profile

### 示例

在javaconfig中

```java
@Configuration
@Component
//这里表示整个类注册的bean都是在dev模式下才启用的bean
@Profile("dev")
public class ProfileExample {
	@Bean
	//这里表示这个bean在模式为dev1下才启用的bean
	@Profile("dev1")
	UserDao userDao() {
		return new UserDaoImpl();
	}
}
```

在xml中配置**profile**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
	profile="dev" >
	<!-- 这里的dev表示整个xml文件定义的类都是在dev模式下才生效 -->
	<!-- 这里的dev1代表的是这个beans 里面的文件 都是在dev1模式下才生效 -->
	<beans profile="dev1">
	</beans>

	<!-- 可以有多个beans -->
	<beans profile="dev2">
	</beans>
</beans>
```

这些配置都只有**激活profile**后才能生效

## 激活profile

spring in action p104

Spring在确定哪个profile处于激活状态时， 需要依赖两个独立的属性： **spring.profiles.active**和**spring.profiles.default。** 

**设置的方法有如下几种**

- 作为DispatcherServlet的初始化参数；
- 作为Web应用的上下文参数；
- 作为JNDI条目；
- 作为环境变量；
- 作为JVM的系统属性；
- 在集成测试类上， 使用@ActiveProfiles注解设置。

这里给个在web.xml的设置方式

![context设置profile](img\context设置profile.png)

![servlet设置profile](img\servlet设置profile.png)

![测试中改变profile](img\测试中改变profile.png)

## 条件化bean

spring in action p107

除了profile Spring还提供了让我们根据不同的条件创建不同的bean,没有达到条件的bean就不进行创建了。

通过@Conditional(MagicConditional.class)这个注解可以进行条件化创建bean

MagicConditional.class要实现Condition接口，才能使用![实现condition接口](img\实现condition接口.png)

这里的 mathes方法 返回true 则条件成立,创建bean

否则 条件不成立,  不创建bean

当然我们可以用metadata和context两个参数进行其他的校验,profile在spring4进行重构,就是利用这两个参数进行校验的

## 解决bean的歧义性

spring in action p111

### 使用场景

我们在很多时候,一个接口的实现类有多个,那么在使用@AutoWrite这个自动注入时,就会抛出异常,因为它找到了多个类型相同的bean,不知道用哪个bean

### 解决方案

- 使用@Primary注解 , 或者在xml中bean的标签里添加 primary=true
- 使用@Qualifier注解,或者在xml中bean的标签里添加 Qualifier="自定义的值"
- 通过自定义注解,强化@Qualifier

**@Qualifier("限定值")**

```java
//声明限定值
@Qualifier("aaa")
class A{}

class B{
    @Autowired
    //查找限定值
    @Qualifier("aaa")
    A a;
    // @Qualifier("aaa")会自动的去找有这个注释 @Qualifier的 且值为 aaa的类
    //@Autowried 就会自动找到类型为A 的bean
}

```

Qualifier在xml 中的使用

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.0.xsd">
	
	<bean id="user" class="com.demo01.entity.User">
		<qualifier value="qqq"></qualifier>
	</bean>
</beans>
```



当我们有多个自定义的限定值的时候，由于java是不能使用同一个注解多次的，所以如果我们有多个限定的时候，我们可以使用自定义注解

自定义注解定义

![自定义限定注解1](img\自定义限定注解1.png)

![自定义限定注解2](img\自定义限定注解2.png)

自定义注解使用

![自定义限定注解使用1](img\自定义限定注解使用1.png)

![自定义限定注解使用2](img\自定义限定注解使用2.png)

## bean的作用域

spring in action p117

- 单例（Singleton） ： 在整个应用中， 只创建bean的一个实例。
- 原型（Prototype） ： 每次注入或者通过Spring应用上下文获取的时候， 都会创建一个新的bean实例。
- 会话（Session） ： 在Web应用中， 为每个会话创建一个bean实例。
- 请求（Rquest） ： 在Web应用中， 为每个请求创建一个bean实例。

定义方式

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)

当然你也可以使用字符串，例如

@Scope("prototype")

**以上的声明都是声明在javaconfig中的@Bean下面的或者是@Component 下面的**

也就是说，作用域是声明在 bean的定义中的，而不是写在 setter field 或者是 constructor中的

在xml中的定义为

```xml
<bean id="user" class="com.demo01.entity.User" scope="singleton">
		<qualifier value="qqq"></qualifier>
</bean>
```



### session 的购物车bean

spring in action p119

我们知道,购物车都是一个用户一个单独的购物车，因此我们不妨把购物车的bean对象的生命周期设置为session

那么怎么使用session呢?

![session的bean声明](img\session的bean声明.png)

定义要声明为session生命周期的类

**其中 proxyMode 为代理模式**，这里设置代理模式为接口，因此 ShoppingCart 要是一个接口

如果不是一个接口的话，就要换个代理模式，换为ScopedProxyMode.TARGET_CLASS ,这个使用cglib代理，可以使用普通类进行代理

![session的Bean注入](img\session的Bean注入.png)

通过以上的就能完成session Bean的使用了

在xml 中也可以修改代理模式

![aop修改代理模式](img\aop修改代理模式.png)

上面用到了aop 的命名空间，因此要导入相应的tdd文件等资源

![aop命名空间的导入](img\aop命名空间的导入.png)

## spring 运行时注入值

spring in action p122

尽管我们使用了构造注入，set注入等方式，其实都是通过硬编码的形式注入的，也就是开启了spring容器(applicationcontext)之后，这个值就被具体的确定下来了，如果想要修改，只能重新运行容器，这是非常不好的，我们需要的是，当我们用到的时候再来获取值，所以也就有了引用外部文件进行设置值

### 导入properties进行赋值

这里就贴一下我经常使用的吧，具体的还是看书吧

```java
package com.demo01.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

import com.demo01.entity.User2;

//声明为配置类
@ContextConfiguration
//导入资源文件
@PropertySource("classpath:user.properties")
public class PlachHolderConfig {
	//注册bean
	@Bean
	User2 user2() {
		return new User2();
	}
}
```



```java
package com.demo01.entity;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

//这里用的是lombok的属性,作用是自动生成tostring get set hashcode 等函数
@Data
public class User2 {
	//获取id值
	@Value("${id}")
	private Integer id;
	//获取username值
	@Value("${username}")
	private String username;
	//获取password值
	@Value("${password}")
	private String password;
}
```

当然还有另一种方法

![environment获取properties](img\environment获取properties.png)

这个environment可以使用 autowire 就可以获取了

```java
@Autowired
	//自动获取spring Environment 
	//当然我们也可以通过这个方法获得 properties 的值 profile 的值等等
	Environment env;
```

## SPEL表达式

spring in action p127

### SpEL基本使用

![SpEL使用](img\SpEL使用.png)

### **SpEL计算数值**

![SpEL使用2](img\SpEL使用2.png)



引用bean直接使用#{bean的id}就可了

### SpEL调用方法

![SpEL使用3](img\SpEL使用3.png)



​	T()运算是专门使用指定某个类型的

例如:

T(java.lang.System)

T(java.lang.Math).PI 获取math的pi值

## AOP(面向切面编程)

### 通知(advice)

通知定义了切面是什么以及何时使用。 除了描述切面要完成的工作，通知还解决了何时执行这个工作的问题。 它应该应用在某个方法被调用之前？ 之后？ 之前和之后都调用？ 还是只在方法抛出异常时调用？

**Spring切面可以应用5种类型的通知：**

- 前置通知（Before） ： 在目标方法被调用之前调用通知功能；
- 后置通知（After） ： 在目标方法完成之后调用通知， 此时不会关心方法的输出是什么；
- 返回通知（After-returning） ： 在目标方法成功执行之后调用通知；
- 异常通知（After-throwing） ： 在目标方法抛出异常后调用通知；
- 环绕通知（Around） ： 通知包裹了被通知的方法， 在被通知的方法调用之前和调用之后执行自定义的行为

### 连接点(Join Point)

我们的应用可能也有数以千计的时机应用通知。 这些时机被称为连接点。 连接点是在应用执行过程中能够插入切面的一个点。 这个点可以是**调用方法时**、 **抛出异常时**、 甚至**修改一个字段**时。 切面代码可以利用这些点插入到应用的正常流程之中， 并添加新的行为。

### 切点(Pointcut)

如果说通知定义了切面的“什么”和“何时”的话， 那么**切点**就定义了“**何处**”。 切点的定义会匹配通知所要织入的一个或多个连接点。 我们通常使用明确的类和方法名称， 或是利用正则表达式定义所匹配的类和方法名称来指定这些切点。 有些AOP框架允许我们创建动态的切点， 可以根据运行时的决策（比如方法的参数值） 来决定是否应用通
知。

### 切面(Aspect)

切面是通知和切点的结合。 通知和切点共同定义了切面的全部内容——它是什么， 在何时和何处完成其功能。

### 引入（Introduction）

**引入允许我们向现有的类添加新方法或属性**。 例如， 我们可以创建一个Auditable通知类， 该类记录了对象最后一次修改时的状态。 这很简单， 只需一个方法， setLastModified(Date)， 和一个实例变量来保存这个状态。 然后， 这个新方法和实例变量就可以被引入到现有的类中， 从而可以在无需修改这些现有的类的情况下， 让它们具
有新的行为和状态。

### 织入（Weaving）

织入是把切面应用到目标对象并创建新的代理对象的过程。 切面在指定的连接点被织入到目标对象中。 在目标对象的生命周期里有多个点可以进行织入：

- 编译期： 切面在目标类编译时被织入。 这种方式需要特殊的编译器。 AspectJ的织入编译器就是以这种方式织入切面的。
- 类加载期： 切面在目标类加载到JVM时被织入。 这种方式需要特殊的类加载器（ClassLoader） ， 它可以在目标类被引入应用之前增强该目标类的字节码。 AspectJ 5的加载时织入（load-timeweaving， LTW） 就支持以这种方式织入切面。
- 运行期： 切面在应用运行的某个时刻被织入。 一般情况下， 在织入切面时， AOP容器会为目标对象动态地创建一个代理对象。Spring AOP就是以这种方式织入切面的。

## 通过表达式来定义切点

spring in action p144

| 注解           | 通知                               |
| -------------- | ---------------------------------- |
| args()         | 匹配有某个参数的方法               |
| @args()        | 匹配参数上有某个注解的方法         |
| @AfterThrowing | 通知方法会在目标方法抛出异常后调用 |
| @Around        | 通知方法会将目标方法封装起来       |
| @Before        | 通知方法会在目标方法调用之前执行   |



![切点表达式](img\切点表达式.jpg)

## 五大切面的通知

spring in action p149

|      注解       |                   通知                   |
| :-------------: | :--------------------------------------: |
|     @After      | 通知方法会在目标方法返回或抛出异常后调用 |
| @AfterReturning |      通知方法会在目标方法返回后调用      |
| @AfterThrowing  |    通知方法会在目标方法抛出异常后调用    |
|     @Around     |       通知方法会将目标方法封装起来       |
|     @Before     |     通知方法会在目标方法调用之前执行     |

## 定义切点@Pointcut

spring in action p150

定义切点只是为了写的切面表达式可以重复利用，所以写法大体上是相同的

使用@Pointcut("切点表达式")

## 开启AOP自动代理

spring in action p151

写好切面之后，最重要的是要开启aop自动代理，如果不开启的话，写得切面是不起作用的

在**javaconfig**中

要在config类上添加类的注解@EnableAspectJAutoProxy

在**xml**中要加入如下的代码

```xml
<aop:aspectj-autoproxy />
```

## 创建环绕通知 @Around

spring in action p152

环绕通知是最为强大的通知类型。环绕通知可以说是其他四种通知的加和。也就是它包含了其他四种通知的功能

使用环绕通知的时候，定义环绕通知方法中的参数必须要有一个参数 **ProceeddingJoinPoint  pjp**

如果没有包含这个参数的话，是会报错的

后面我们要利用这个参数进行操作，也就是我们通过这个参数可以拦截方法的执行，它调用 pjp.proceed();这个函数才能使目标方法执行，不然的话目标方法是不执行的

### 有关JoinPoint的介绍

```java
public interface JoinPoint {  
    String toString();         //连接点所在位置的相关信息  
    String toShortString();     //连接点所在位置的简短相关信息  
    String toLongString();     //连接点所在位置的全部相关信息  
    Object getThis();         //返回AOP代理对象  
    Object getTarget();       //返回目标对象  
    Object[] getArgs();       //返回被通知方法参数列表  
    Signature getSignature();  //返回当前连接点签名  
    SourceLocation getSourceLocation();//返回连接点方法所在类文件中的位置  
    String getKind();        //连接点类型  
    StaticPart getStaticPart(); //返回连接点静态部分  
}  
```

### 有关ProceedingJoinPoint的介绍

```java
public interface ProceedingJoinPoint extends JoinPoint {  
    public Object proceed() throws Throwable;  
    public Object proceed(Object[] args) throws Throwable;  
}
```

### JoinPoint.StaticPart的介绍

```java
public interface StaticPart {  
    Signature getSignature();    //返回当前连接点签名  
    String getKind();          //连接点类型  
    int getId();               //唯一标识  
    String toString();         //连接点所在位置的相关信息  
    String toShortString();     //连接点所在位置的简短相关信息  
    String toLongString();     //连接点所在位置的全部相关信息  
}  
```

在一些普通的通知上可以使用JoinPoint 这个参数 ，当然也可以不用，看你的应用场景决定

```java
@Before(value="execution(* sayBefore(*))")  
public void before(JoinPoint jp) {}  
   
@Before(value="execution(* sayBefore(*))")  
public void before(JoinPoint.StaticPart jp) {}  
```

在环绕通知@Around中**必须**要有 **ProceedingJoinPoint** 这个参数

获取执行的方法名称

getSinature().getName()这样就可以获取方法名称了



## 一些常用的切点写法

```java
	//所有返回值 无论是不是公有方法 任意参数 的方法名为show的 切点
	@Pointcut("execution( * show(..) )")
	public void show() {}
	//所有方法都执行这个函数
	@Pointcut("execution(* *(..) )")
	public void allMethod() {}
	//含有参数名为user 且类型为 User 的方法执行
	@Pointcut("args(user)")
	public void args(User user) {}
	
	//含有某个注释的方法执行此方法
	@Pointcut("@annotation(dateTimeFormat)")
	public void annotation(DateTimeFormat dateTimeFormat) {}
	//所有公有方法
	@Pointcut("execution( public * *(..) )")
	public void allPublic() {}
	
	//在com.aop.demo01.service 包范围内的所有方法
	//@Pointcut("within(com.aop.demo01.service.*)")
	//在demo01这个包及所有子包的所有方法
	@Pointcut("within(com.aop.demo01..*)")
	public void withinInService() {}
	
	@Pointcut("within(com.aop.demo01.**)")
	public void withinInDemo01() {}	
```



## SpringMVC 最新的配置方式

spring in action p177

我们知道传统的Spring MVC 需要配置一大堆的xml文件

但是在servlet3.0 和spring3.0之后 逐渐推出了一种简化的方式进行配置 那就是定义注解类

![SpringMVC注解配置](img\SpringMVC注解配置.png)

这里就是基本的配置

### 最小可用的mvc配置

![最小可用的mvc配置](img\最小可用的mvc配置.png)

### 基本的控制器

```java
@Controller
public class HomeController{
    //由于这里的类没有加上@RequestMapping 注解 所以访问这里的页面就只需要访问/就可以访问home页面了
    //如果类有@RequestMapping 注解 访问的路径就是 /类的映射/方法的映射 才能访问得到
    @RequestMapping(value="/",method=GET)
    public String home(){
        return "home";
    }
}
```

这上面的控制器是在设置**首页**也就是访问localhost:8080/项目名 就可以访问到 **/WEB-INF/views/home.jsp** 这个页面

### 测试控制器

![测试控制器](K:\gitRepository\spring-study\img\测试控制器.png)



