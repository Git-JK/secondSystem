package webdevelopment.secondsystem.service.Impl;


import com.alibaba.fastjson2.JSON;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import webdevelopment.secondsystem.domain.entity.Dormitory;
import webdevelopment.secondsystem.service.RedisService;
import webdevelopment.secondsystem.utils.exception.RedisSetErrorException;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public <T> Boolean set(String key, T value) {
        Boolean result = false;
        try {
            System.out.println(redisTemplate);
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            throw new RedisSetErrorException("redis插入数据失败");
        }
        return result;
    }

    @Override
    public<T> Boolean set(String key, T value, Integer timeout, TimeUnit timeUnit) {
        Boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
            result = true;
        } catch (Exception e) {
            throw new RedisSetErrorException("redis插入数据失败");
        }
        return result;
    }

    @Override
    public Boolean expire(String key, Long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    @Override
    public Boolean expire(String key, Long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public <T> T get(String key) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    @Override
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Long delete(Collection collection) {
        return redisTemplate.delete(collection);
    }

    @Override
    public <T> Long setList(String key, List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    @Override
    public <T> List<T> getList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    @Override
    public <T> BoundSetOperations<String, T> setSet(String key, Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while(it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    @Override
    public <T> Set<T> getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public <T> HashOperations<String, String, T> setMap(String key, Map<String, T> dataMap) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if(dataMap != null) {
            for(Map.Entry<String, T> entry: dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    @Override
    public <T> Map<String, T> getMap(String key) {
        Map<String, T> map = redisTemplate.opsForHash().entries(key);
        return map;
    }

    @Override
    public Collection<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public List<Dormitory> getDormitoryListByConditions(Integer buildingId, Integer neededBedNumber, String gender) {
        String key = String.valueOf(buildingId) + "_" + gender + "_" + String.valueOf(neededBedNumber);
        return getList(key);
    }

    @Override
    public Long setDormitoryListByConditions(Integer buildingId, Integer neededBedNumber, String gender, List<Dormitory> dormitoryList) {
        String key = String.valueOf(buildingId) + "_" + gender + "_" + String.valueOf(neededBedNumber);
        return setList(key, dormitoryList);
    }

    /**
     * bean 转 String
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    /**
     * string转bean
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }
}
