package webdevelopment.secondsystem.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Label implements Serializable {
    private String name;
    private String description;
}
