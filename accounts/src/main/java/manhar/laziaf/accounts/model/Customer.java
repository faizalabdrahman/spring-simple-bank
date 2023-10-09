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
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Long customerId;

    private String name;
    private String email;
    private String mobileNumber;

    @Builder
    public Customer(Timestamp createdDate, String createdBy, Timestamp lastModifiedDate, String modifiedBy,
                    Long customerId, String name, String email, String mobileNumber) {
        super(createdDate, createdBy, lastModifiedDate, modifiedBy);
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }
}
