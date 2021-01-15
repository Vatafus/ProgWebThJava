package finalproject.jw.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    @NotBlank(message = "Invalid name!")
    private String name;

    @Email(message = "Invalid email!")
    private String email;
}
