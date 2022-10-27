package webdevelopment.secondsystem.domain.entity;

import lombok.Data;

@Data
public class StudentBedPair {
    private Integer buildingId;
    private Integer roomId;
    private Integer bedId;
    private Long studentId;
}
