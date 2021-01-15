package finalproject.jw.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ResponseDTO {

    private LocalDateTime time;
    private Integer statusCode;
    private String message;

    public ResponseDTO(Integer statusCode, String message) {
        this.time = LocalDateTime.now();
        this.statusCode = statusCode;
        this.message = message;
    }
}
