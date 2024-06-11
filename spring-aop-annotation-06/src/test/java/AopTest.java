import com.hrc.staticproxy.CalculatorImp;
import com.hrc.staticproxy.CalculatorProxy;
import org.junit.jupiter.api.Test;

public class AopTest {
    @Test
    public void staticProxyTest(){
        CalculatorImp calculatorImp=new CalculatorImp();
        CalculatorProxy proxy=new CalculatorProxy(calculatorImp);
        int sum = proxy.add(1, 2);
    }
}
