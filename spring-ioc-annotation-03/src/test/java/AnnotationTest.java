import com.hrc.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationTest {
    @Test
    public void annotationTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("Spring-ioc-packge-01.xml");
        MyController bean = context.getBean(MyController.class);
        System.out.println(bean);
    }
}
