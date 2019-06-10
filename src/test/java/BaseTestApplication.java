import com.qmdx00.IotApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author yuanweimin
 * @date 19/06/10 10:50
 * @description 测试类父类
 */
@WebAppConfiguration
@SpringBootTest(classes = IotApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTestApplication {

    @Before
    public void before() {
        System.out.println("=========开始测试=========");
    }

    @After
    public void after() {
        System.out.println("=========结束测试=========");
    }
}
