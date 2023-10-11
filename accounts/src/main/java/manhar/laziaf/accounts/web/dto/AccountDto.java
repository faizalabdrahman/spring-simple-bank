package manhar.laziaf.accounts.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {

    @NotEmpty(message = "Name cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{9})", message = "Account number must be 9 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account type must be SAVINGS or CURRENT")
    private String accountType;

    @NotEmpty(message = "Branch address cannot be null or empty")
    private String branchAddress;
}
