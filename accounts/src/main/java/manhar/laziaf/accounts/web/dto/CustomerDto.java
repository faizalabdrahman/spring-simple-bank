package manhar.laziaf.accounts.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto extends BaseItem {

    private String name;
    private String email;
    private String mobileNumber;

    private AccountDto accountDto;

    @Builder
    public CustomerDto(OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, String name, String email,
                       String mobileNumber, AccountDto accountDto) {
        super(createdDate, lastModifiedDate);
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.accountDto = accountDto;
    }
}
