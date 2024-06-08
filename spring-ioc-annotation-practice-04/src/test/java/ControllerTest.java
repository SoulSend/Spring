import com.hrc.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ControllerTest {
    //一次编写整合mvc框架就成功
    @Test
    public void testRun(){
        //初始化一个ioc容器
        ApplicationContext context =new ClassPathXmlApplicationContext("spring-bean-01.xml");
        //从容器里获取controller的bean
        StudentController bean = context.getBean(StudentController.class);
        bean.findAll();
    }
}
