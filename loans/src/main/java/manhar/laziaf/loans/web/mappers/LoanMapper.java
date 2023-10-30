package manhar.laziaf.loans.web.mappers;


import manhar.laziaf.loans.model.Loan;
import manhar.laziaf.loans.web.dto.LoanDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface LoanMapper {

    LoanDto loanToLoanDto(Loan loan);

    Loan loanDtoToLoan(LoanDto loanDto);
}
