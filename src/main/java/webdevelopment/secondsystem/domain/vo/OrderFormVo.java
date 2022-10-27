package webdevelopment.secondsystem.domain.vo;

import lombok.Data;

@Data
public class OrderFormVo {
    private Integer buildingId;
    private String applyTime;
    private Integer applyMemberNumber;
    private String applyMemberIdList;
    private String applyMemberCodeList;
    private String gender;
}
