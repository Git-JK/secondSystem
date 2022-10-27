package webdevelopment.secondsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import webdevelopment.secondsystem.dao.DormitoryMapper;
import webdevelopment.secondsystem.domain.entity.Dormitory;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DormitoryMapperTests {
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Test
    public void insert() {
        Dormitory dormitory = new Dormitory();
        dormitory.setRoomId(5111);
        dormitory.setBuildingId(5);
        dormitory.setBedCountAll(4);
        dormitory.setBedCountAvailable(4);
        dormitory.setUserGender("1");
        dormitory.setBedCountFree(4);
        dormitory.setIsEmpty("1");
        dormitory.setRoomMemberIdList(null);
        Integer rows = dormitoryMapper.insert(dormitory);
        System.out.println(rows);
    }
    @Test
    public void findByRoomAndBuildingId() {
        Integer roomId = 9111;
        Integer buildingId = 9;
        Dormitory result = dormitoryMapper.findByRoomAndBuildingId(roomId, buildingId);
        System.out.println(result);
    }
    @Test
    public void findFreeDormitoriesByBuildingId() {
        Integer buildingId = 9;
        List<Dormitory> result = dormitoryMapper.findFreeDormitoriesByBuildingId(buildingId);
        for(Dormitory dorm : result) {
            System.out.println(dorm);
        }
    }
    @Test
    public void update() {
        Dormitory dormitory = new Dormitory();
        dormitory.setId(25);
        dormitory.setRoomId(5111);
        dormitory.setBuildingId(5);
        dormitory.setBedCountAll(6);
        dormitory.setBedCountAvailable(6);
        dormitory.setUserGender("1");
        dormitory.setBedCountFree(6);
        dormitory.setIsEmpty("1");
        dormitory.setRoomMemberIdList(null);
        Integer rows = dormitoryMapper.update(dormitory);
        System.out.println(rows);
    }
    @Test
    public void deleteByRoomAndBuildingId() {
        Integer roomId = 5111;
        Integer buildingId = 5;
        Integer rows = dormitoryMapper.deleteByRoomAndBuildingId(roomId, buildingId);
        System.out.println(rows);
    }

    @Test
    public void findFreeDormitoriesByNeededBedNumberAndGender() {
        Integer neededBedNumber = 6;
        String gender = "1";
        List<Dormitory> dormitoryList = dormitoryMapper.findFreeDormitoriesByNeededBedNumberAndGender(neededBedNumber, gender);
        for(Dormitory dorm: dormitoryList) {
            System.out.println(dorm);
        }
    }
}
