package webdevelopment.secondsystem.service;

import webdevelopment.secondsystem.domain.entity.Building;
import webdevelopment.secondsystem.domain.entity.Dormitory;
import webdevelopment.secondsystem.domain.entity.OrderForm;

import java.util.List;

public interface BuildingService {
    /**
     * 获得可以入住的宿舍楼列表
     * @return 返回可入住的Building List
     */
    List<Building> getAvailableBuilding();

    /**
     * 根据订单来修改宿舍楼数据
     * @param orderForm
     * @return 受影响行数
     */
    Integer updateBuilding(OrderForm orderForm, Dormitory dormitory);
}
