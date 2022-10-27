package webdevelopment.secondsystem.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import webdevelopment.secondsystem.domain.dto.OrderFormDto;
import webdevelopment.secondsystem.domain.vo.OrderFormVo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderForm {
    private Integer orderId;
    private Integer buildingId;
    private String applyTime;
    private Integer applyMemberNumber;
    private String applyMemberIdList;
    private String applyMemberCodeList;
    private String orderStatus;
    private String gender;

    /**
     * 对order中的student id list进行处理
     * @return student id list
     */
    public List<String> getMemberIdList() {
        List<String> memberIdList = new ArrayList<>(Arrays.asList(applyMemberIdList.trim().split(" ")));
        return memberIdList;
    }

    /**
     * 对order中的student code list进行处理
     * @return
     */
    public List<String> getMemberCodeList() {
        List<String> memberCodeList = new ArrayList<>(Arrays.asList(applyMemberCodeList.trim().split(" ")));
        return memberCodeList;
    }
    public OrderForm(OrderFormDto orderFormDto) {
        this.orderId = orderFormDto.getOrderId();
        this.buildingId = orderFormDto.getBuildingId();
        this.applyTime = orderFormDto.getApplyTime();
        this.applyMemberNumber = orderFormDto.getApplyMemberNumber();
        this.applyMemberIdList = orderFormDto.getApplyMemberIdList();
        this.applyMemberCodeList = orderFormDto.getApplyMemberCodeList();
        this.orderStatus = orderFormDto.getOrderStatus();
        this.gender = orderFormDto.getGender();
    }
}
