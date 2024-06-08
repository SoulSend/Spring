import com.alibaba.druid.pool.DruidDataSource;
import com.hrc.Configuration.MyConfiguration;
import com.hrc.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest {
    private ApplicationContext context=new AnnotationConfigApplicationContext(MyConfiguration.class);
    @Test
    public void configTest(){
        MyController bean = context.getBean(MyController.class);
        bean.getMessage();
        System.out.println();
    }
}
