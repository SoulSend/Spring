import com.hrc.dynamicproxy.ProxyFactory;
import com.hrc.staticproxy.Calculator;
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
    @Test
    public void dynamicProxyTest(){
        ProxyFactory factory = new ProxyFactory(new CalculatorImp());
        Calculator proxy = (Calculator) factory.getProxy();
        proxy.add(1,0);
        //proxy.div(1,1);
    }
}
