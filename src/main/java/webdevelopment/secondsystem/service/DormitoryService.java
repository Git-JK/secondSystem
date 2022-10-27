package webdevelopment.secondsystem.service;

import webdevelopment.secondsystem.domain.entity.Dormitory;

import java.util.List;

public interface DormitoryService {
    /**
     * 根据需要的床数和性别以及楼号查找可以申请的宿舍
     * @param neededBedNumber
     * @param gender
     * @return 返回可申请的宿舍列表
     */
    List<Dormitory> getAvailableDormitoryList(Integer buildingId, Integer neededBedNumber, String gender);

    /**
     * 根据宿舍id查询宿舍信息
     * @param roomId
     * @return 返回对应宿舍（若存在），否则返回NULL
     */
    Dormitory getDormitoryById(Integer roomId, Integer buildingId);
}
