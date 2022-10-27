package webdevelopment.secondsystem.domain.entity;

import lombok.Data;

@Data
public class Bed {
    private Integer id;
    private Integer buildingId;
    private Integer roomId;
    private Integer bedId;
    private String isAvailable;
    private String isFree;
}
