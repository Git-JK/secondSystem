package webdevelopment.secondsystem.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentBedPair implements Serializable {
    private Integer buildingId;
    private Integer roomId;
    private Integer bedId;
    private Long studentId;
}
