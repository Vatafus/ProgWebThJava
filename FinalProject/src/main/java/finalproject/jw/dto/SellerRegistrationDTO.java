package finalproject.jw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerRegistrationDTO {

    @NotBlank(message = "Seller's legal name can't be blank!")
    private String legalName;

    @NotBlank(message = "Seller's business name can't be blank!")
    private String businessName;

    @Valid
    @NotNull(message = "Seller's bank account can't be null!")
    private BankACCRegistrationDTO account;
}
