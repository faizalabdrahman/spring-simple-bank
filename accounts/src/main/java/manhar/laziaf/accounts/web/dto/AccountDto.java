package manhar.laziaf.accounts.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto extends BaseItem {

    private Long accountNumber;
    private String accountType;
    private String branchAddress;

    @Builder
    public AccountDto(OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, Long accountNumber,
                      String accountType, String branchAddress) {
        super(createdDate, lastModifiedDate);
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
    }
}
