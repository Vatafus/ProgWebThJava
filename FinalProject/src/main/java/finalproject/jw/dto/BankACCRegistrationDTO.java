package finalproject.jw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankACCRegistrationDTO {
    @NotBlank(message = "IBAN can't be blank!")
    private String iban;

    @NotNull
    @PositiveOrZero(message  = "Bank account's balance can't be negative!")
    private Double balance;
}
