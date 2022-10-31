package webdevelopment.secondsystem.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Bed implements Serializable {
    private Integer id;
    private Integer buildingId;
    private Integer roomId;
    private Integer bedId;
    private String isAvailable;
    private String isFree;
}
