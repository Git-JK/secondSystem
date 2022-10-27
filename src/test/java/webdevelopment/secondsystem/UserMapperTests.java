package webdevelopment.secondsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import webdevelopment.secondsystem.domain.entity.User;
import webdevelopment.secondsystem.dao.UserMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan(basePackages = "webdevelopment.secondsystem.**")
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert() {
        User user = new User();
        user.setStudentId(Long.valueOf("2201210590"));
        user.setName("郭耀齐");
        user.setGender("1");
        user.setPhoneNumber("18310960388");
        user.setStatus("0");
        user.setLabel("英杰三苑");
        user.setPassword("123456");
        user.setHasBedroom("0");
        user.setBedroomId(null);
        user.setVerificationCode("226547");
        user.setUserType("3");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUserId() {
        Long userId = Long.valueOf("2201210608");
        User result = userMapper.findByUserId(userId);
        System.out.println(result);
    }

    @Test
    public void deleteByUserId() {
        Long userId = Long.valueOf("2201210590");
        Integer rows = userMapper.deleteByUserId(userId);
        System.out.println(rows);
    }

    @Test
    public void update() {
        User user = new User();
        user.setStudentId(Long.valueOf("2201210590"));
        user.setName("郭耀齐");
        user.setGender("1");
        user.setPhoneNumber("18310960388");
        user.setStatus("0");
        user.setLabel("英杰三苑");
        user.setPassword("123456");
        user.setHasBedroom("0");
        user.setBedroomId(null);
        user.setVerificationCode("226547");
        user.setUserType("3");
        Integer rows = userMapper.update(user);
        System.out.println(rows);
    }
}
