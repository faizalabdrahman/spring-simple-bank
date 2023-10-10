package manhar.laziaf.accounts.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private String name;
    private String email;
    private String mobileNumber;

    private AccountDto accountDto;
}
