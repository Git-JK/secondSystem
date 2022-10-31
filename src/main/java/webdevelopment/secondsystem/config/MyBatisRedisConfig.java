package webdevelopment.secondsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import webdevelopment.secondsystem.cache.MybatisCacheImpl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class MyBatisRedisConfig {
    @Resource
    RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        MybatisCacheImpl.setTemplate(redisTemplate);
    }
}
