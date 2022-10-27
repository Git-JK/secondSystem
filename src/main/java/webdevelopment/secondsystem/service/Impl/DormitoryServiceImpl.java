package webdevelopment.secondsystem.service.Impl;

import org.springframework.stereotype.Service;
import webdevelopment.secondsystem.dao.DormitoryMapper;
import webdevelopment.secondsystem.domain.entity.Dormitory;
import webdevelopment.secondsystem.service.DormitoryService;

import javax.annotation.Resource;
import java.util.List;


@Service
public class DormitoryServiceImpl implements DormitoryService {
    @Resource
    private DormitoryMapper dormitoryMapper;

    @Override
    public List<Dormitory> getAvailableDormitoryList(Integer buildingId, Integer neededBedNumber, String gender) {
        return dormitoryMapper.findFreeDormitoriesByNeededBedNumberAndGender(neededBedNumber, gender);
    }

    @Override
    public Dormitory getDormitoryById(Integer roomId, Integer buildingId) {
        return dormitoryMapper.findByRoomAndBuildingId(roomId, buildingId);
    }
}
