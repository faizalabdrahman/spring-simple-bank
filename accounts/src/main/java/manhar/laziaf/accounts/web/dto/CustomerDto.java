package manhar.laziaf.accounts.web.dto;

import jakarta.validation.constraints.Email;
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
public class CustomerDto {

    @NotEmpty(message = "Name cannot be null or empty")
    private String name;

    @Email(message = "Email address must be a valid format")
    @NotEmpty(message = "Name cannot be null or empty")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{8})", message = "Mobile number must be 8 digits")
    private String mobileNumber;

    private AccountDto accountDto;
}