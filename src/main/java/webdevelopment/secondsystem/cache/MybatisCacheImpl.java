package webdevelopment.secondsystem.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Set;

@Slf4j
public class MybatisCacheImpl implements Cache {
    @Resource
    private static RedisTemplate redisTemplate;
    private String id;

    public MybatisCacheImpl(String id) {
        this.id = id;
    }

    public static void setTemplate(RedisTemplate template) {
        MybatisCacheImpl.redisTemplate = template;
    }
    @Override
    public String getId() {
        return id;
    }


    @Override
    public void putObject(Object key, Object value) {
        log.debug("设置缓存");
        redisTemplate.opsForValue().set(key.toString(), value);
    }

    @Override
    public Object getObject(Object key) {
        log.debug("缓存的id:" + this.id);
        log.debug("查询缓存");
        System.out.println(key.toString());
        System.out.println(redisTemplate);
        return redisTemplate.opsForValue().get(key.toString());
    }

    @Override
    public Object removeObject(Object key) {
        log.debug("删除缓存");
        redisTemplate.delete(key.toString());
        return null;
    }

    @Override
    public void clear() {
        log.debug("清空缓存");
        Set<String> keys = redisTemplate.keys("*" + this.id + "*");
        redisTemplate.delete(keys);
    }

    @Override
    public int getSize() {
        log.debug("获得缓存中所有key的size");
        Integer execute = (Integer) redisTemplate.execute(new RedisCallback<Integer>() {
            @Override
            public Integer doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize().intValue();
            }
        });
        return execute;
    }


}
