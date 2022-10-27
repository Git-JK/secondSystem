package webdevelopment.secondsystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import webdevelopment.secondsystem.domain.entity.Bed;

@Repository
@Mapper
public interface BedMapper {
    /**
     * 插入一条床数据
     * @param bed
     * @return 返回受影响行数
     */
    Integer insert(Bed bed);

    /**
     * 根据楼、宿舍、床id删除一条床数据
     * @param buildingId
     * @param roomId
     * @param bedId
     * @return 返回受影响行数
     */
    Integer deleteById(Integer buildingId, Integer roomId, Integer bedId);

    /**
     * 根据楼、宿舍、床id查询一条床数据
     * @param buildingId
     * @param roomId
     * @param bedId
     * @return 如果找到对应床数据则返回，否则返回NULL
     */
    Bed findById(Integer buildingId, Integer roomId, Integer bedId);

    /**
     * 更新一条床数据
     * @param bed
     * @return 返回受影响行数
     */
    Integer update(Bed bed);
}
