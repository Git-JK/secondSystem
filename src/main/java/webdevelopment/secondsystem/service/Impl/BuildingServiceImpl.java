package webdevelopment.secondsystem.service.Impl;

import org.springframework.stereotype.Service;
import webdevelopment.secondsystem.dao.BuildingMapper;
import webdevelopment.secondsystem.domain.entity.Building;
import webdevelopment.secondsystem.domain.entity.Dormitory;
import webdevelopment.secondsystem.domain.entity.OrderForm;
import webdevelopment.secondsystem.service.BuildingService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Resource
    private BuildingMapper buildingMapper;
    @Override
    public List<Building> getAvailableBuilding() {
        return buildingMapper.findAvailableBuilding();
    }
    @Override
    public Integer updateBuilding(OrderForm orderForm, Dormitory dormitory) {
        Integer buildingId = orderForm.getBuildingId();
        Integer applyMemberNumber = orderForm.getApplyMemberNumber();
        Building building = buildingMapper.findByBuildingId(buildingId);
        building.setBedCountFree(building.getBedCountFree() - applyMemberNumber);
        if(dormitory.getBedCountFree() <= 0) {
            building.setRoomCountFree(building.getRoomCountFree() - 1);
        }
        Integer rows = buildingMapper.update(building);
        return rows;
    }
}
