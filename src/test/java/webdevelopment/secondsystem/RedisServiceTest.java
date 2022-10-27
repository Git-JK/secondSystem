package webdevelopment.secondsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import webdevelopment.secondsystem.service.RedisService;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisServiceTest {
    @Resource
    private RedisService redisService;

    @Test
    public void set() {
        String key = "myName";
        String value = "JK";
        redisService.set(key, value);
    }

    @Test
    public void expire() {
        String key = "myName";
        Long timeout = Long.valueOf(10);
        redisService.expire(key, timeout);
    }

    @Test
    public void get() {
        String key = "Name";
        String value = redisService.get(key);
        System.out.println(value);
    }

    @Test

    public void delete() {
        String key = "myName";
        redisService.delete(key);
    }
}
