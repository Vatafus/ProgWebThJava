package finalproject.jw.dto;


import lombok.Data;

import javax.validation.constraints.Positive;


public class CreateDTO extends ResponseDTO {
    @Positive(message = "Id can't be negative or zero")
    private Long id;

    public CreateDTO(Integer statusCode, String message, Long id) {
        super(statusCode, message);
        this.id = id;
    }
}
