package webdevelopment.secondsystem.service;


import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import webdevelopment.secondsystem.domain.entity.Dormitory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisService {
    /**
     * 缓存基本对象，Integer、String、实体类等
     * @param key 缓存的键值
     * @param value 缓存的值
     * @return 缓存成功与否
     * @param <T>
     */
    <T> Boolean set(String key, T value);

    /**
     * 缓存基本对象，Integer，String，实体类等
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     * @return
     * @param <T>
     */
    <T> Boolean set(String key, T value, Integer timeout, TimeUnit timeUnit);

    /**
     * 设置有效时间
     * @param key redis key
     * @param timeout 超时时间
     * @param timeUnit 时间单位
     * @return true设置成功，false设置失败
     */
    Boolean expire(String key, Long timeout, TimeUnit timeUnit);

    /**
     * 设置有效时间
     * @param key redis key
     * @param timeout 超时时间
     * @return true设置成功，false设置失败
     */
    Boolean expire(String key, Long timeout);

    /**
     * 获得缓存的基本对象
     * @param key 缓存key
     * @return 缓存key对应的数据
     * @param <T>
     */
    <T> T get(String key);

    /**
     * 删除单个对象
     * @param key
     * @return
     */
    Boolean delete(String key);

    /**
     * 删除集合对象
     * @param collection 多个对象
     * @return
     */
    Long delete(Collection collection);

    /**
     * 缓存List数据
     * @param key
     * @param dataList
     * @return
     * @param <T>
     */
    <T> Long setList(String key, List<T> dataList);

    /**
     * 获得缓存的List对象
     * @param key
     * @return
     * @param <T>
     */
    <T> List<T> getList(String key);

    /**
     * 缓存Set
     * @param key
     * @param dataSet
     * @return
     * @param <T>
     */
    <T> BoundSetOperations<String, T> setSet(String key, Set<T> dataSet);

    /**
     * 获得缓存的Set
     * @param key
     * @return
     * @param <T>
     */
    <T> Set<T> getSet(String key);

    /**
     * 缓存Map
     * @param key
     * @param dataMap
     * @return
     * @param <T>
     */
    <T> HashOperations<String, String, T> setMap(String key, Map<String, T> dataMap);

    /**
     * 获得缓存的Map
     * @param key
     * @return
     * @param <T>
     */
    <T> Map<String, T> getMap(String key);

    /**
     * 获得缓存的基本对象列表
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    Collection<String> keys(String pattern);

    /**
     * 获得缓存中的对应宿舍楼、性别有至少多少床位的宿舍的信息
     * @param buildingId
     * @param neededBedNumber
     * @param gender
     * @return 返回宿舍列表（若有满足条件的宿舍），否则返回NULL
     */
    List<Dormitory> getDormitoryListByConditions(Integer buildingId, Integer neededBedNumber, String gender);

    Long setDormitoryListByConditions(Integer buildingId, Integer neededBedNumber, String gender, List<Dormitory> dormitoryList);
}
