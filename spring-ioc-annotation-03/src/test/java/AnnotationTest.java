import com.hrc.ioc01.MyController;
import com.hrc.ioc02.controller.SoldierController;
import com.hrc.ioc3.ValueController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationTest {
    private ApplicationContext context=new ClassPathXmlApplicationContext("Spring-ioc-packge-01.xml");
    @Test
    public void annotationTest(){
        MyController bean = context.getBean(MyController.class);
        System.out.println(bean);
    }
    @Test
    public void autoWiredTest(){
        SoldierController bean = context.getBean(SoldierController.class);
        bean.getMessage();
    }
    @Test
    public void valuePrint(){
        ValueController bean = context.getBean(ValueController.class);
        bean.printName();
        bean.printPassword();
    }


}
