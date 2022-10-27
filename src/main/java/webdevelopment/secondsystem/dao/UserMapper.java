package webdevelopment.secondsystem.dao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import webdevelopment.secondsystem.domain.entity.User;

@Repository
@Mapper
public interface UserMapper {
    /**
     * 插入一条用户数据
     * @param user
     * @return 受影响行数
     */
    Integer insert(User user);

    /**
     * 根据用户id来查询用户数据
     * @param userId
     * @return 如果找到对应用户则返回对应用户的数据，否则返回NULL值
     */
    User findByUserId(Long userId);

    /**
     * 根据用户id来删除一条用户
     * @param userId
     * @return 返回受影响行数
     */
    Integer deleteByUserId(Long userId);

    /**
     * 更新一条数据
     * @param user
     * @return 返回受影响行数
     */
    Integer update(User user);
}
