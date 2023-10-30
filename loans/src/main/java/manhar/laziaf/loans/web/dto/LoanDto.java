package manhar.laziaf.loans.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanDto {

    @NotEmpty(message = "Mobile number must not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{8})", message = "Mobile number must be 8 digits")
    private String mobileNumber;

    @NotEmpty(message = "Loan number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{9})", message = "Loan number must be 12 digits")
    private String loanNumber;

    @NotEmpty(message = "Loan type must be PERSONAL, MORTGAGE or EDUCATION")
    private String loanType;

    @Positive(message = "Total loan amount must be greater than zero")
    private int totalLoan;

    @PositiveOrZero(message = "Amount paid must be greater than zero")
    private int amountPaid;

    @PositiveOrZero(message = "Outstanding amount must be greater than zero")
    private int outstandingAmount;
}
