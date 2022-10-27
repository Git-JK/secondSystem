package webdevelopment.secondsystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import webdevelopment.secondsystem.domain.entity.Building;

import java.util.List;

@Repository
@Mapper
public interface BuildingMapper {
    /**
     * 插入一条宿舍楼数据
     * @param building
     * @return 受影响行数
     */
    Integer insert(Building building);

    /**
     * 根据宿舍楼id来查询宿舍楼数据
     * @param BuildingId
     * @return 如果找到对应宿舍楼则返回对应宿舍楼的数据，否则返回NULL值
     */
    Building findByBuildingId(Integer BuildingId);

    /**
     * 根据宿舍楼id来删除一条数据
     * @param BuildingId
     * @return 返回受影响行数
     */
    Integer deleteByBuildingId(Integer BuildingId);

    /**
     * 更新一条数据
     * @param building
     * @return 返回受影响行数
     */
    Integer update(Building building);

    /**
     * 查询可以选的宿舍楼列表
     * @return 可选的宿舍楼列表
     */
    List<Building> findAvailableBuilding();
}
