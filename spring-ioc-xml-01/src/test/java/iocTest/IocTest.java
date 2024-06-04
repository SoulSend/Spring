package iocTest;

import com.ioc.XML.excp1.HappyComponent;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocTest {
    @Test
    public  void instantIocAndGetBeans(){
        //方式1:实例化并且指定配置文件
//参数        ：String...locations 传入一个或者多个配置文件
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-bean-01.xml");
        //方式2:先实例化，再指定配置文件，最后刷新容器触发Bean实例化动作 实现类特有，接口没有刷新方法和指定配置方法
       /* ClassPathXmlApplicationContext context1=new ClassPathXmlApplicationContext();
        context1.setConfigLocation("spring-bean-02.xml");
        //刷新
        context1.refresh();*/
    }
    //从ioc容器里面获取bean对象
    @Test
    public void getBean(){
        ApplicationContext context=new ClassPathXmlApplicationContext("spring-bean-03.xml");
        //bean id 获取bean
        HappyComponent happyComponent = (HappyComponent)context.getBean("happyComponent");
        //类型获取 bean
        HappyComponent bean = context.getBean(HappyComponent.class);
        bean.doWork();
        System.out.println("call bean ");
        happyComponent.doWork();
        System.out.println("call happyComponent");
    }
    

}
