package manhar.laziaf.loans.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Loan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "loan_id")
    private Long loanId;
    private String mobileNumber;
    private String loanNumber;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;
    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;

    @Builder
    public Loan(Timestamp createdDate, String createdBy, Timestamp lastModifiedDate, String modifiedBy, Long loanId,
                String mobileNumber, String loanNumber, LoanType loanType, int totalLoan, int amountPaid,
                int outstandingAmount) {
        super(createdDate, createdBy, lastModifiedDate, modifiedBy);
        this.loanId = loanId;
        this.mobileNumber = mobileNumber;
        this.loanNumber = loanNumber;
        this.loanType = loanType;
        this.totalLoan = totalLoan;
        this.amountPaid = amountPaid;
        this.outstandingAmount = outstandingAmount;
    }
}
