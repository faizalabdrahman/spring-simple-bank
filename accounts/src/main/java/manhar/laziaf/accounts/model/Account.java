package manhar.laziaf.accounts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account extends BaseEntity {

    private Long customerId;

    @Id
    private Long accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String branchAddress;

    @Builder
    public Account(Timestamp createdDate, String createdBy, Timestamp lastModifiedDate, String modifiedBy,
                   Long customerId, Long accountNumber, AccountType accountType, String branchAddress) {
        super(createdDate, createdBy, lastModifiedDate, modifiedBy);
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
    }
}
