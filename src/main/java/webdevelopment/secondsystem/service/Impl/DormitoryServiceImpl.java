package webdevelopment.secondsystem.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public List<Dormitory> getAvailableDormitoryList(Integer buildingId, Integer neededBedNumber, String gender) {
        return dormitoryMapper.findFreeDormitoriesByConditions(buildingId, neededBedNumber, gender);
    }

    @Override
    @Transactional
    public Dormitory getDormitoryById(Integer roomId, Integer buildingId) {
        return dormitoryMapper.findByRoomAndBuildingId(roomId, buildingId);
    }
}
