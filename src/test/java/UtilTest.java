import com.qmdx00.util.TimeUtil;
import com.qmdx00.util.TokenUtil;
import com.qmdx00.util.UUIDUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanweimin
 * @date 19/06/10 10:54
 * @description 工具类测试
 */
public class UtilTest extends BaseTestApplication {

    @Autowired
    private TokenUtil tokenUtil;

    @Test
    public void generate() {
        Map<String, String> map = new HashMap<>();
        map.put("uuid", UUIDUtil.getUUID());
        String token = tokenUtil.createJwt(map, 1000 * 10);
        System.out.println(token);
        System.out.println(tokenUtil.getClaim(token, "uuid").asString());
    }

    @Test
    public void timeTest() {
        Date date = TimeUtil.toDate("1560911938548");
        System.out.println(date);
    }
}
