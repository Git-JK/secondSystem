package webdevelopment.secondsystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import webdevelopment.secondsystem.domain.entity.Dormitory;

import java.util.List;

@Repository
@Mapper
public interface DormitoryMapper {
    /**
     * 插入一条宿舍数据
     * @param dormitory
     * @return 返回受影响的行数
     */
    Integer insert(Dormitory dormitory);

    /**
     * 根据宿舍id和宿舍楼号查找宿舍数据
     * @param roomId
     * @param buildingId
     * @return 如果找到则返回对应宿舍数据，否则返回NULL
     */
    Dormitory findByRoomAndBuildingId(Integer roomId, Integer buildingId);

    /**
     * 根据宿舍id和宿舍楼号删除宿舍数据
     * @param roomId
     * @param buildingId
     * @return 返回受影响行数
     */
    Integer deleteByRoomAndBuildingId(Integer roomId, Integer buildingId);

    /**
     * 更新一个宿舍数据
     * @param dormitory
     * @return 返回受影响行数
     */
    Integer update(Dormitory dormitory);

    /**
     * 根据宿舍楼号寻找该宿舍楼可住的宿舍列表
     * @param buildingId
     * @return
     */
    List<Dormitory> findFreeDormitoriesByBuildingId(Integer buildingId);

    /**
     * 据需要的床数和性别查找可以申请的宿舍
     * @param neededBedNumber
     * @param gender
     * @return 返回可申请的宿舍列表
     */
    List<Dormitory> findFreeDormitoriesByConditions(Integer buildingId, Integer neededBedNumber, String gender);
}
