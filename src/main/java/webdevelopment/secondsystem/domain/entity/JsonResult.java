package webdevelopment.secondsystem.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult<T> implements Serializable {
    private Integer state;
    private String message;
    private T data;
    public JsonResult() {
        super();
    }
    public JsonResult(Integer state) {
        super();
        this.state = state;
    }
    public JsonResult(Throwable e) {
        super();
        this.message = e.getMessage();
    }
    public JsonResult(Integer state, T data) {
        super();
        this.state = state;
        this.data = data;
    }
    public JsonResult(Integer state, String message, T data) {
        super();
        this.state = state;
        this.message = message;
        this.data = data;
    }
}
