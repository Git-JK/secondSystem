package webdevelopment.secondsystem.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import webdevelopment.secondsystem.domain.vo.OrderFormVo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderFormDto {
    private Integer orderId;
    private Integer buildingId;
    private Integer roomId;
    private String roomMemberIdList;
    private String applyTime;
    private Integer applyMemberNumber;
    private String applyMemberIdList;
    private String applyMemberCodeList;
    private String orderStatus;
    private String gender;

    /**
     * 使用OrderFormVo初始化OrderFormDto
     * @param orderFormVo
     */
    public OrderFormDto(OrderFormVo orderFormVo) {
        this.buildingId = orderFormVo.getBuildingId();
        this.applyTime = orderFormVo.getApplyTime();
        this.applyMemberNumber = orderFormVo.getApplyMemberNumber();
        this.applyMemberIdList = orderFormVo.getApplyMemberIdList();
        this.applyMemberCodeList = orderFormVo.getApplyMemberCodeList();
        this.gender = orderFormVo.getGender();
    }

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
}
