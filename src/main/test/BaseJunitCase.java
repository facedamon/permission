import com.facedamon.param.UserParam;
import com.facedamon.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: facedamon
 * @Description:
 * @Date: Credted in 下午10:29 2018/7/1
 * @Modified by:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BaseJunitCase {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void test(){
        UserParam userParam = new UserParam();
        userParam.setUsername("admin");
        userParam.setMail("adminTest@qq.com");
        userParam.setStatus(1);
        userParam.setTelephone("1119285050");
        userParam.setDeptId(2);
        userParam.setRemark("admin test");
        userParam.setId(1);
        sysUserService.update(userParam);
    }
}
