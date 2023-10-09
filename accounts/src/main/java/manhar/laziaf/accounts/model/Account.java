package manhar.laziaf.accounts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String accountNumber;
    private String accountType;
    private String branchAddress;

    @Builder
    public Account(Timestamp createdDate, String createdBy, Timestamp lastModifiedDate, String modifiedBy,
                   Long customerId, String accountNumber, String accountType, String branchAddress) {
        super(createdDate, createdBy, lastModifiedDate, modifiedBy);
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.branchAddress = branchAddress;
    }
}
