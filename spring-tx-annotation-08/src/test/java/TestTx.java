import com.hrc.config.myConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import com.hrc.service.StudentService;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitConfig(myConfig.class)
public class TestTx {
    @Autowired
    private StudentService service;
    @Test

    public void testService(){
        service.changeInfo();
    }
}
