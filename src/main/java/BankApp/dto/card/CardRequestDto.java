package BankApp.dto.card;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDto {
    @NotEmpty(message = "Card number cannot be empty!")
    @Pattern(
            regexp = "^[0-9]{16}$",
            message = "Card number must contain exactly 16 digits!"
    )
    String cardNumber;

    @Pattern(
            regexp = "^(0[1-9]|1[0-2])/\\d{2}$",
            message = "Date must be in format MM/YY"
    )
    String expireDate;

    @NotEmpty(message = "CVV cannot be empty!")
    @Pattern(
            regexp = "^[0-9]{3}$",
            message = "CVV must be exactly 3 digits!"
    )
    String cvv;
}
