package manhar.laziaf.loans.services;

import manhar.laziaf.loans.web.dto.LoanDto;

public interface LoanService {

    LoanDto createLoan(String mobileNumber);

    LoanDto getLoan(String mobileNumber);

    LoanDto updateLoan(LoanDto loanDto);

    boolean deleteLoan(String mobileNumber);
}
