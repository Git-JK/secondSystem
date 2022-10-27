package webdevelopment.secondsystem.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Building implements Serializable {
    private Integer buildingId;
    private String userGender;
    private Integer roomCountAll;
    private Integer bedCountAll;
    private Integer roomCountFree;
    private Integer bedCountFree;
}
