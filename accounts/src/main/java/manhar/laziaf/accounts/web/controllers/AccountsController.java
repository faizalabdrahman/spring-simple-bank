package manhar.laziaf.accounts.web.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import manhar.laziaf.accounts.constants.AccountsConstants;
import manhar.laziaf.accounts.services.AccountService;
import manhar.laziaf.accounts.web.dto.AccountDto;
import manhar.laziaf.accounts.web.dto.CustomerDto;
import manhar.laziaf.accounts.web.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    private final AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid CustomerDto customerDto) {
        return new ResponseEntity<>(accountService.createAccount(customerDto), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam
                                                             @Pattern(regexp = "(^$|[0-9]{8})", message = "Mobile Number must be numeric")
                                                             String mobileNumber) {
        return new ResponseEntity<>(accountService.getAccount(mobileNumber), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody @Valid CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = accountService.updateAccount(customerDto);
        if (updatedCustomerDto != null) {
            return new ResponseEntity<>(new ResponseDto(AccountsConstants.MESSAGE_200), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto(AccountsConstants.MESSAGE_500), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{8})", message = "Mobile Number must be numeric")
                                                                String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return new ResponseEntity<>(new ResponseDto(AccountsConstants.MESSAGE_200), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto(AccountsConstants.MESSAGE_500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
