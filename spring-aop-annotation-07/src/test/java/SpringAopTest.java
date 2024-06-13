import com.hrc.config.AopConfig;
import com.hrc.service.Calculator;
import com.hrc.service.imp.CalculatorImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(value = AopConfig.class)
public class SpringAopTest {
    @Autowired
    private Calculator calculator;
    @Test
    public void calcTest(){
        calculator.add(1, 1);
        //异常通知测试
        //calculator.div(1,0);
    }
}
