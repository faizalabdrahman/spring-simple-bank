package manhar.laziaf.loans.web.controllers;

import jakarta.validation.constraints.Pattern;
import manhar.laziaf.loans.services.LoanService;
import manhar.laziaf.loans.web.dto.LoanDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/loans", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoansController {

    private final LoanService loanService;

    public LoansController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/create")
    public ResponseEntity<LoanDto> createLoan(@RequestParam
                                              @Pattern(regexp = "(^$|[0-9]{8})", message = "Mobile Number must be numeric")
                                              String mobileNumber) {
        return new ResponseEntity<>(loanService.createLoan(mobileNumber), HttpStatus.CREATED);
    }
}
