package webdevelopment.secondsystem.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webdevelopment.secondsystem.dao.*;
import webdevelopment.secondsystem.domain.dto.OrderFormDto;
import webdevelopment.secondsystem.domain.entity.*;
import webdevelopment.secondsystem.service.BuildingService;
import webdevelopment.secondsystem.service.OrderService;
import webdevelopment.secondsystem.utils.exception.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderFormMapper orderFormMapper;
    @Resource
    private BuildingMapper buildingMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private DormitoryMapper dormitoryMapper;
    @Resource
    private BuildingService buildingService;
    @Override
    @Transactional
    public Boolean dataCheck(OrderFormDto orderFormDto) {
        Integer buildingId = orderFormDto.getBuildingId();
        Building building = buildingMapper.findByBuildingId(buildingId);
        if(building == null) {
            throw new BuildingNotFoundException("宿舍楼不存在");
        }
        if(building.getUserGender() != "0" && !building.getUserGender().equals(orderFormDto.getGender())) {
            throw new GenderNotMatchException("性别与宿舍不匹配");
        }
        Integer applyMemberNumber = orderFormDto.getApplyMemberNumber();
        List<String> memberIdList = orderFormDto.getMemberIdList();
        List<String> memberCodeList = orderFormDto.getMemberCodeList();
        if(memberIdList.size() != applyMemberNumber || memberCodeList.size() != applyMemberNumber) {
            throw new MemberNumberNotMatchException("学号数量/验证码数量不匹配");
        }
        String lastGender = "-1";
        for(int i = 0; i < applyMemberNumber; i++) {
            Long studentId = Long.valueOf(memberIdList.get(i));
            String VerificationCode = memberCodeList.get(i);
            User user = userMapper.findByUserId(studentId);
            if(user == null) {
                throw new UserNotFoundException("用户不存在");
            }
            if(!user.getVerificationCode().equals(VerificationCode)) {
                throw new VerificationCodeNotMatchException("验证码不匹配");
            }
            if(user.getBedroomId() != null) {
                throw new UserHadRoomException("用户已选择宿舍");
            }
            if(lastGender.equals("-1")) {
                lastGender = user.getGender();
            }
            else {
                if(!lastGender.equals(user.getGender())) {
                    throw new GenderNotMatchException("队伍性别不统一");
                }
            }
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean timeCheck(OrderFormDto orderFormDto) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date startTime;
        try {
            Date orderTime = format.parse(orderFormDto.getApplyTime());
            startTime = format.parse("2022-10-23 14:00:00");
            if (orderTime.compareTo(startTime) >= 0) {
                return true;
            }
            throw new TimeNotMatchException("不在系统时间内,不可以申请宿舍");
        }
        catch (ParseException e) {
            throw new TimeFormatNotMatchException("时间格式不正确");
        }

    }

    @Override
    @Transactional
    public OrderFormDto orderProcessing(OrderFormDto orderFormDto) {
        OrderFormDto result = new OrderFormDto();
        OrderForm orderForm = new OrderForm(orderFormDto);
        result.setOrderId(orderFormDto.getOrderId());
        result.setBuildingId(orderFormDto.getBuildingId());
        result.setApplyTime(orderFormDto.getApplyTime());
        result.setApplyMemberNumber(orderFormDto.getApplyMemberNumber());
        result.setApplyMemberIdList(orderFormDto.getApplyMemberIdList());
        result.setApplyMemberCodeList(orderFormDto.getApplyMemberCodeList());
        result.setGender(orderFormDto.getGender());
        //判断是否数据合法以及时间合法
        if(!timeCheck(orderFormDto) || !dataCheck(orderFormDto)) {
            orderForm.setOrderStatus("0");
            result.setOrderStatus("0");
            Integer rows = orderFormMapper.insert(orderForm);
            return result;
        }
        //寻找可申请宿舍，若为0则订单状态置为0（失败）并返回处理结果
        Integer buildingId = orderForm.getBuildingId();
        String orderGender = orderForm.getGender();
        Integer applyMemberNumber = orderForm.getApplyMemberNumber();
        List<Dormitory> freeDormitoryList = dormitoryMapper.findFreeDormitoriesByConditions(buildingId, applyMemberNumber, orderGender);
        if(freeDormitoryList.size() == 0) {
            result.setOrderStatus("0");
            orderForm.setOrderStatus("0");
            Integer rows = orderFormMapper.insert(orderForm);
            return result;
        }
        //随机选择一个宿舍并将其塞进去，并修改宿舍信息
        Random random = new Random();
        Dormitory dormitory = freeDormitoryList.get(random.nextInt(freeDormitoryList.size()));
        dormitory.setBedCountFree(dormitory.getBedCountFree() - applyMemberNumber);
        if(dormitory.getBedCountFree() == 0) {
            dormitory.setIsEmpty("0");
        }
        if(dormitory.getRoomMemberIdList() != null) {
            dormitory.setRoomMemberIdList(dormitory.getRoomMemberIdList().trim() + " " + orderForm.getApplyMemberIdList().trim());
        }
        else {
            dormitory.setRoomMemberIdList(orderForm.getApplyMemberIdList().trim());
        }
        //将宿舍信息更新
        Integer rows = dormitoryMapper.update(dormitory);
        //根据order和宿舍信息修改宿舍楼信息
        buildingService.updateBuilding(orderForm, dormitory);
        //修改对应学生信息
        List<String> memberIdList = orderForm.getMemberIdList();
        for(String id : memberIdList) {
            User student = userMapper.findByUserId(Long.valueOf(id));
            student.setBedroomId(dormitory.getRoomId());
            userMapper.update(student);
        }
        //将order状态和处理结果状态修改，然后order存储，结果返回
        result.setRoomMemberIdList(dormitory.getRoomMemberIdList());
        result.setRoomId(dormitory.getRoomId());
        result.setOrderStatus("1");
        orderForm.setOrderStatus("1");
        orderFormMapper.insert(orderForm);
        return result;
    }

    @Override
    @Transactional
    public OrderFormDto getOrderForm(Integer orderId) {
        OrderForm orderForm = orderFormMapper.findById(orderId);
        if(orderForm == null) {
            throw new OrderNotFoundException("订单不存在");
        }
        OrderFormDto orderFormDto = new OrderFormDto();
        orderFormDto.setOrderId(orderForm.getOrderId());
        orderFormDto.setBuildingId(orderForm.getBuildingId());
        orderFormDto.setApplyTime(orderForm.getApplyTime());
        orderFormDto.setApplyMemberNumber(orderForm.getApplyMemberNumber());
        orderFormDto.setApplyMemberCodeList(orderForm.getApplyMemberCodeList());
        orderFormDto.setApplyMemberCodeList(orderForm.getApplyMemberCodeList());
        orderFormDto.setOrderStatus(orderForm.getOrderStatus());
        orderFormDto.setGender(orderForm.getGender());
        return orderFormDto;
    }
}
