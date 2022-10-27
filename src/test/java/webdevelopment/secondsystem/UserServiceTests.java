package webdevelopment.secondsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import webdevelopment.secondsystem.domain.entity.User;
import webdevelopment.secondsystem.service.IUserService;
import webdevelopment.secondsystem.utils.exception.ServiceException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService iuserService;

    @Test
    public void login() {
        try {
            Long userId = Long.valueOf("2201210608");
            String password = "jinke200098cs";
            User user = iuserService.login(userId, password);
            System.out.println("login successfully!" + user);
        } catch (ServiceException e) {
            System.out.println("login failed!" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
