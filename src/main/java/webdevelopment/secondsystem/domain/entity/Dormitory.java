package webdevelopment.secondsystem.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dormitory implements Serializable {
    private Integer id;
    private Integer roomId;
    private Integer buildingId;
    private Integer bedCountAll;
    private Integer bedCountAvailable;
    private String userGender;
    private Integer bedCountFree;
    private String isEmpty;
    private String roomMemberIdList;
}
