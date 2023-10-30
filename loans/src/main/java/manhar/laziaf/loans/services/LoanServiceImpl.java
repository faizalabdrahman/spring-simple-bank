package manhar.laziaf.loans.services;

import manhar.laziaf.loans.constants.LoansConstants;
import manhar.laziaf.loans.exceptions.LoanAlreadyExistsException;
import manhar.laziaf.loans.model.Loan;
import manhar.laziaf.loans.model.LoanType;
import manhar.laziaf.loans.repositories.LoanRepository;
import manhar.laziaf.loans.web.dto.LoanDto;
import manhar.laziaf.loans.web.mappers.LoanMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    public LoanServiceImpl(LoanRepository loanRepository, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
    }

    @Override
    public LoanDto createLoan(String mobileNumber) {
        Optional<Loan> loanOptional = loanRepository.findByMobileNumber(mobileNumber);
        if (loanOptional.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with the given mobile number " + mobileNumber);
        }
        Loan loan = createNewLoan(mobileNumber);
        loan.setCreatedBy("System");
        Loan savedLoan = loanRepository.save(loan);
        return loanMapper.loanToLoanDto(savedLoan);
    }

    @Override
    public LoanDto getLoan(String mobileNumber) {
        //todo
        return null;
    }

    @Override
    public LoanDto updateLoan(LoanDto loanDto) {
        //todo
        return null;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        //todo
        return false;
    }

    private Loan createNewLoan(String mobileNumber) {
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        Loan newLoan = Loan.builder()
                .loanNumber(Long.toString(randomLoanNumber))
                .mobileNumber(mobileNumber)
                .loanType(LoanType.EDUCATION)
                .totalLoan(LoansConstants.NEW_LOAN_LIMIT)
                .amountPaid(0)
                .outstandingAmount(LoansConstants.NEW_LOAN_LIMIT)
                .build();
        return newLoan;
    }
}
