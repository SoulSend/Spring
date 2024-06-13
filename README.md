# SSM框架

尚硅谷的SSMu框架的课件：[快速掌握：全新SSM+Spring Boot+MyBatis-Plus实战精讲 (wolai.com)](https://www.wolai.com/v5Kuct5ZtPeVBk4NBUGBWF)

源码的远程仓库：[SoulSend/Spring: 学习SpringIoc容器和id的代码仓库 (github.com)](https://github.com/SoulSend/Spring)

## Spring framework 

#### 介绍：这个框架是整个spring生态的基座，就干两件事：控制反转（IOC）和依赖注入（DI）

**控制反转：**就是将创建对象的权力交给容器，我们开发者就不用创建对象了，让容器给我们创建和管理对象。

**依赖注入：**DI 是指在组件之间传递依赖关系的过程中，将依赖关系在容器内部进行处理，这样就不必在应用程序代码中硬编码对象之间的依赖关系，实现了对象之间的解耦合。在 Spring 中，DI 是通过 XML 配置文件或注解的方式实现的。它提供了三种形式的依赖注入：构造函数注入、Setter 方法注入和接口注入。

**容器**：有简单容器和复杂容器，简单容器就只有存储功能如：数组；复杂容器不仅有存储功能还有管理存储的内容的功能

**组件：**组件就是一个可重复利用的对象，这个对象一般再容器里是单例的

**框架：**框架和工具的区别：工具就是一个jar包，它是写死的，你只能这么用。而框架是：jar包加上配置文件，也就是说，框架可以根据配置文件来动态的改变功能，这就是他们俩的区别

#### 使用XML配置IOC容器的ioc/di

##### 使用步骤：

- 先编写你需要放入ioc容器的类

- 兴建一个xml文件，如果你已经导入spring框架，在idea中可以快捷建立一个XML配置文件，然后选中Spring配置文件就可以快速生成一个自带spring标签约束的xml文件

- 然后在xml文件中使用 <bean> 标签配置你要放入容器的类案例：

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
  
  
      <!-- 导入外部属性文件 -->
      <context:property-placeholder location="classpath:jdbc.properties" />
  
      <!-- 配置数据源 -->
      <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
          <property name="url" value="${atguigu.url}"/>
          <property name="driverClassName" value="${atguigu.driver}"/>
          <property name="username" value="${atguigu.username}"/>
          <property name="password" value="${atguigu.password}"/>
      </bean>
  
      <!-- 配置 JdbcTemplate -->
      <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
          <!-- 装配数据源 -->
          <property name="dataSource" ref="druidDataSource"/>
      </bean>
  
  
      <bean id="studentDao" class="com.atguigu.dao.impl.StudentDaoImpl">
          <property name="jdbcTemplate" ref="jdbcTemplate" />
      </bean>
  
      <bean id="studentService" class="com.atguigu.service.impl.StudentServiceImpl">
          <property name="studentDao" ref="studentDao" />
      </bean>
  
      <bean id="studentController" class="com.atguigu.controller.StudentController">
          <property name="studentService" ref="studentService" />
      </bean>
  
  </beans>
  ```

- id 属性是你放入的bean对象在ioc容器中唯一标识，在依赖注入时会用到

- class 是目标类的全类名

- 当然，你可以指定一个bean对象的构造方法：构造器，工厂，这时的标签属性会有不同，可以看我远程仓库里的源码

- 打开bean标签里面，这个是di的配置，property是setter方法进行注入，name是setter方法去掉set，然后首字母小写的方法名字，value指定的是一个值，ref指定的是一个bean对象，这两个是setter方法的参数

- 然后就可以初始化一个ioc容器，获取bean对象了

  更具体可以查看课件和源码。

#### 使用注解配置ioc容器

#### ioc配置使用步骤：

- 准备你要放入容器的类

- 在不同的项目层面会有不同的注解，功能都是一模一样的，仅仅只是名字不一样，为了方便程序员标识不同的项目层次：

  ```txt
  @Component：该注解用于描述 Spring 中的 Bean，它是一个泛化的概念，仅仅表示容器中的一个组件（Bean），并且可以作用在应用的任何层次，例如 Service 层、Dao 层等。 使用时只需将该注解标注在相应类上即可。
  
  @Repository：该注解用于将数据访问层（Dao 层）的类标识为 Spring 中的 Bean，其功能与 @Component 相同。
  
  @Service|该注解通常作用在业务层（Service 层），用于将业务层的类标识为 Spring 中的 Bean，其功能与 @Component 相同。
  
  @Controller：该注解通常作用在控制层（如SpringMVC 的 Controller），用于将控制层的类标识为 Spring 中的 Bean，其功能与 @Component 相同。
  
  ```

- 将注解放到对应的项目层次

- 配置包扫描

  ```xml
  <!-- 配置自动扫描的包 -->
      <!-- 1.包要精准,提高性能!
           2.会扫描指定的包和子包内容
           3.多个包可以使用,分割 例如: com.atguigu.controller,com.atguigu.service等
      -->
      <context:component-scan base-package="com.atguigu.components"/>
  <!-- 情况三：指定不扫描的组件 -->
  <context:component-scan base-package="com.atguigu.components">
      
      <!-- context:exclude-filter标签：指定排除规则 -->
      <!-- type属性：指定根据什么来进行排除，annotation取值表示根据注解来排除 -->
      <!-- expression属性：指定排除规则的表达式，对于注解来说指定全类名即可 -->
      <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
  </context:component-scan>
  
  <!-- 情况四：仅扫描指定的组件 -->
  <!-- 仅扫描 = 关闭默认规则 + 追加规则 -->
  <!-- use-default-filters属性：取值false表示关闭默认扫描规则 -->
  <context:component-scan base-package="com.atguigu.ioc.components" use-default-filters="false">
      
      <!-- context:include-filter标签：指定在原有扫描规则的基础上追加的规则 -->
      <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
  </context:component-scan>
  ```

- ioc容器内部的bean类名首字母小写就是 bean 的 id。例如：SoldierController 类对应的 bean 的 id 就是 soldierController。

##### 你可在注解后面指定value属性来设置bean的类名

##### 引用DI配置步骤:

- 将你要注入的所有类装配进入ioc容器
- 在你要注入的属性上面添加@Autowired注解

##### 注解流程：

- 首先根据所需要的**组件类型**到 IOC 容器中查找
- 能够找到唯一的 bean：直接执行装配
- 如果完全找不到匹配这个类型的 bean：装配失败
- 和所需类型匹配的 bean 不止一个
    - 没有 @Qualifier 注解：根据 @Autowired 标记位置成员变量的变量名作为 bean 的 id 进行匹配
        - 能够找到：执行装配
        - 找不到：装配失败
    - 使用 @Qualifier 注解：根据 @Qualifier 注解中指定的名称作为 bean 的id进行匹配
        - 能够找到：执行装配
        - 找不到：装配失败

如果注解下是一个接口，接口有多个实现类就会出现多个bean对象的异常抛出

##### 扩展JSR-250注解@Resource，这个注解就相当于：@Autowired + @Qualifier

##### 基本类型DI配置：

- 1、直接将值赋给基础类型

- 2、使用value可以获取外部配置文件里的值，但是需要在xml文件里读取外部配置文件，然后使用value注解获取配置文件里的值

  ```XML
  <!--读取配置文件-->
      <context:property-placeholder location="value.properties"/>
      
  @Value("${value.username}")
      private  String username;
  ```




#### 配置类（完全注解开发）

##### 使用配置类来代替XMl配置文件，用注解@Configuration来标注。在配置类里使用注解来配置ioc和di这样就脱离了xml配置文件，达成完全注解开发的目的

使用步骤：

先使用@Configuration标注这是一个配置类然后使用两个注解

@Componentscan是包扫描注解

@PropertySource是引用外部的配置文件

在配置类的内部，使用@bean标签来标注一个组件，这个注解放在一个方法上，方法的返回值就是bean的类型，方法名就是bean的id。在这个方法里使用你的方法，不管是构造器还是工厂类来构建一个bean对象，同时使用setter方法来进行初始化。

读取配置类以后，要使用变量装配配置文件里的值，可以先使用Value注解将值赋给一个普通变量，然后通过set方法传参将这个变量注入到对应的bean对象里

##### @Import注解的使用

@Import 注释允许从另一个配置类加载 @Bean 定义，如以下示例所示：

```java
@Configuration
public class ConfigA {

  @Bean
  public A a() {
    return new A();
  }
}

@Configuration
@Import(ConfigA.class)
public class ConfigB {

  @Bean
  public B b() {
    return new B();
  }
}
```

现在，在实例化上下文时不需要同时指定 ConfigA.class 和 ConfigB.class ，只需显式提供 ConfigB ，如以下示例所示：

```java
public static void main(String[] args) {
  ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigB.class);

  // now both beans A and B will be available...
  A a = ctx.getBean(A.class);
  B b = ctx.getBean(B.class);
}
```

此方法简化了容器实例化，因为只需要处理一个类，而不是要求您在构造期间记住可能大量的 @Configuration 类。



#### AOP面向切面编程

**使用场景：**当一个类的很多个方法都要添加一个相同的逻辑功能的时候，如果我们依次为每个方法添加，这样的效率非常低，且维护成本高，且代码耦合度也很高，这时我们你想了一个办法，将相同的逻辑提炼出来，当调用一个方法的时候，将这个提炼出来的逻辑动态的加入到这个方法中，这个就是AOP面向切面编程。

##### 代理模式：类似房客-----中介-----房东的关系，我们把一个方法的核心逻辑提取出来，非核心逻辑（日志之类的）放到代理类里。这样做的好处：

```
代理模式提供了一种结构化的方法来处理这些逻辑，使得代码更加模块化和易于管理。此外，代理模式允许在不修改目标对象的情况下，通过修改代理类来添加或更改非核心逻辑，这在某些情况下可以大大简化开发和维护工作。
```

**静态代理：**核心逻辑放到目标类的里面，然后代理类拥有一个目标类，我们直接调用代理类的对应的方法，在代理类里面，先有代理类执行非核心逻辑，再由代理类调用本身拥有的目标类的方法，执行核心逻辑。就是这么简单。具体实现查看代码。

```
补充说明：静态代理确实实现了解耦，但是由于代码都写死了，完全不具备任何的灵活性。就拿日志功能来说，将来其他地方也需要附加日志，那还得再声明更多个静态代理类，那就产生了大量重复的代码，日志功能还是分散的，没有统一管理。
提出进一步的需求：将日志功能集中到一个代理类中，将来有任何日志需求，都通过这一个代理类来实现。这就需要使用动态代理技术了。
```

**动态代理：**动态代理是一种在运行时动态创建代理对象的代理模式。

```
动态代理技术分类
- JDK动态代理：JDK原生的实现方式，需要被代理的目标类必须**实现接口**！他会根据目标类的接口动态生成一个代理对象！代理对象和目标对象有相同的接口！（拜把子）
- cglib：通过继承被代理的目标类实现代理，所以不需要目标类实现接口！（认干爹）
```



##### AOP是一种编程思想，是对面向对象编程思想OOP的一种完善，我们使用动态代理的技术来实现AOP。AOP的思想就是将非核心逻辑提取出来成为一个切面，使用动态代理的技术，当我们调用目标类的某一个方法的时候，会将这个切片插入到这个方法里，形成一个代理方法，最后执行的就是这个代理方法

**总结一句话：**AOP编程思想就是解决非核心业务代码冗余的问题的。大白话就是不重要的，但是所有方法都有的逻辑，将这个逻辑代码抽取成一个切片，然后动态分发给每一个方法。



##### AOP编程思想的术语：

- **横切关注点**：就是对非核心的哪些业务场景进行描述的一个术语，不然每次都说非核心业务很麻烦比如日志，事务哪些业务，与之相对的，核心代码业务就叫**核心关注点**
- **通知（增强）：**就是前面说的切片，一个切片就是一个通知。也就是哪些提取出来的横切关注点的代码。每一个横切关注点上都要有一个方法来实现它，这个方法就是通知方法：
  - 前置通知：在被代理的目标类方法执行前的通知
  - 返回通知：目标类的方法执行成功后执行的通知
  - 异常通知：在目标类的方法执行出现异常的时候执行的通知
  - 后置通知：目标类方法最终执行完毕后执行的通知
  - 环绕通知：把目标方法包围的通知
- **连接点：**目标类有很多方法，哪些方法要切入通知，哪些方法不需要切入通知，要切入通知的方法就是连接点，连接点就是用来标识我们通知的方法是哪个？是哪些方法需要切入通知。
- **切入点：**我们有多个连接点，被通知切入的连接点就是切入点
- **切面：**当我们的通知切入连接点以后就形成了一个切面
- **目标：**要被切入的对象或者说被代理的对象就是目标，前面的代理模式介绍过了
- **代理：**作为名词就是：目标类切入通知以后形成的代理类。作为动词就是：形成代理类的动作就叫代理
- **织入：**把通知应用到目标类上，生成代理类的过程就是织入，有静态织入和动态织入，其实就是静态代理和动态代理，spring用的是后者



##### Aop练习案例：

- 1、首先编写核心逻辑代码，将这些核心逻辑的类全部放入ioc容器

- 2、编写一个通知类，同时将这个类放入ioc容器，这个类里包含了你抽取出来的非核心逻辑，类上标注注解@Aspect，表明这是一个通知类

- 3、在通知类里编写具体的通知逻辑，根据通知的执行时间使用不同的注解

- 4、在注解内使用value属性，属性是一个字符串，字符串内写入execution(）这个方法，方法参数就是切入点表达式：

  ```
  @Before(value = "execution(* com.hrc.service.imp.CalculatorImp.add(int ,int))")
  ```

- 5、开启spring的Aspect支持，在配置类上注解@EnableAspectJAutoProxy,或者xml配置文件编写<aop:aspectj-autoproxy />

##### 1、在通知中获取目标类的方法信息

需要获取方法签名、传入的实参等信息时，可以在通知方法声明JoinPoint类型的形参。

- 要点1：JoinPoint 接口通过 getSignature() 方法获取目标方法的签名（方法声明时的完整信息）
- 要点2：通过目标方法签名对象获取方法名
- 要点3：通过 JoinPoint 对象获取外界调用目标方法时传入的实参列表组成的数组

##### 2、方法返回值

在返回通知中，通过@AfterReturning注解的returning属性获取目标方法的返回值！

- 在方法形参中声明你的返回值形参名字，在注解中给returning属性赋值你的形参名，这样就会将方法返回值封装给形参

##### 3、异常对象捕捉

在异常通知中，通过@AfterThrowing注解的throwing属性获取目标方法抛出的异常对象，使用方法同 2 一样



##### AOP切点表达式

**介绍：**说白了就是一个通配字符串，用来匹配切点的

**语法**:

- 第一位：execution( ) 固定开头
- 第二位：方法访问修饰符

```Java
public private 直接描述对应修饰符即可
```
- 第三位：方法返回值

```Java
int String void 直接描述返回值类型

```

    注意：
    
    特殊情况 不考虑 访问修饰符和返回值
    
      execution(* * ) 这是错误语法
    
      execution(*) == 你只要考虑返回值 或者 不考虑访问修饰符 相当于全部不考虑了
- 第四位：指定包的地址

```Java
 固定的包: com.atguigu.api | service | dao
 单层的任意命名: com.atguigu.*  = com.atguigu.api  com.atguigu.dao  * = 任意一层的任意命名
 任意层任意命名: com.. = com.atguigu.api.erdaye com.a.a.a.a.a.a.a  ..任意层,任意命名 用在包上!
 注意: ..不能用作包开头   public int .. 错误语法  com..
 找到任何包下: *..
```
- 第五位：指定类名称

```Java
固定名称: UserService
任意类名: *
部分任意: com..service.impl.*Impl
任意包任意类: *..*

```
- 第六位：指定方法名称

```Java
语法和类名一致
任意访问修饰符,任意类的任意方法: * *..*.*
```
- 第七位：方法参数

```Java
第七位: 方法的参数描述
       具体值: (String,int) != (int,String) 没有参数 ()
       模糊值: 任意参数 有 或者 没有 (..)  ..任意参数的意识
       部分具体和模糊:
         第一个参数是字符串的方法 (String..)
         最后一个参数是字符串 (..String)
         字符串开头,int结尾 (String..int)
         包含int类型(..int..)
```

##### 切点表达式案例

```
1.查询某包某类下，访问修饰符是公有，返回值是int的全部方法
2.查询某包下类中第一个参数是String的方法
3.查询全部包下，无参数的方法！
4.查询com包下，以int参数类型结尾的方法
5.查询指定包下，Service开头类的私有返回值int的无参数方法
```

##### 切点表达式的复用

```
1、可以在定义一个方法，方法名返回值都随意，要无参。在方法上使用注解pointcut 属性calue值就是切点表达式，然后在其他通知方法的注解处将切点表达式修改为这个方法的调用就行
2、创建一个存储切点的类，单独维护切点表达式，然后再通知类里调用这个切点表达式类的对应切点表达式方法
```

##### 环绕通知

​	一个顶前面的所有通知，允许程序猿自己定义通知的位置，通过参数ProceedingJoinPoint 来获取目标方法，执行参数的proceed方法

##### 切面排序

​	在切面类的上面添加注解@Order（数字），参数数字越小优先级越高，越高越先执行

##### 也可以使用XML来配置AOP