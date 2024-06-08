# SSM框架

尚硅谷的SSMu框架的课件：[快速掌握：全新SSM+Spring Boot+MyBatis-Plus实战精讲 (wolai.com)](https://www.wolai.com/v5Kuct5ZtPeVBk4NBUGBWF)

源码的远程仓库：[SoulSend/Spring: 学习SpringIoc容器和id的代码仓库 (github.com)](https://github.com/SoulSend/Spring)

### Spring framework 

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

  ```
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

##### ioc配置使用步骤：

- 准备你要放入容器的类

- 在不同的项目层面会有不同的注解，功能都是一模一样的，仅仅只是名字不一样，为了方便程序员标识不同的项目层次：

  ```
  @Component：该注解用于描述 Spring 中的 Bean，它是一个泛化的概念，仅仅表示容器中的一个组件（Bean），并且可以作用在应用的任何层次，例如 Service 层、Dao 层等。 使用时只需将该注解标注在相应类上即可。
  
  @Repository：该注解用于将数据访问层（Dao 层）的类标识为 Spring 中的 Bean，其功能与 @Component 相同。
  
  @Service|该注解通常作用在业务层（Service 层），用于将业务层的类标识为 Spring 中的 Bean，其功能与 @Component 相同。
  
  @Controller：该注解通常作用在控制层（如SpringMVC 的 Controller），用于将控制层的类标识为 Spring 中的 Bean，其功能与 @Component 相同。
  
  ```

- 将注解放到对应的项目层次

- 配置包扫描

  ```
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

  ```
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