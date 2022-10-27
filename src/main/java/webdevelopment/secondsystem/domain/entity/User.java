package webdevelopment.secondsystem.domain.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Long id;
    private Long studentId;
    private String name;
    private String gender;
    private String phoneNumber;
    private String status;
    private String label;
    private String password;
    private String hasBedroom;
    private Integer bedroomId;
    private String verificationCode;
    private String userType;
}
