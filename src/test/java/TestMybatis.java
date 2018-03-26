import com.yoozoo.ShiroApplication;
import com.yoozoo.domain.User;
import com.yoozoo.mapper.UserMapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Hao on 2018/3/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ShiroApplication.class)
public class TestMybatis {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper(){

        User user = userMapper.findByName("eric");

        System.out.println(user);
    }

    @Test
    public void updatePassword(){
        User user = new User();
        user.setId("1");

        Md5Hash md5Hash = new Md5Hash("1234","eric",2);
        user.setPassword(md5Hash.toString());

        userMapper.updatePassword(user);


    }
}
