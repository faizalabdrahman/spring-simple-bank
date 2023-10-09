package manhar.laziaf.accounts.controllers;

import manhar.laziaf.accounts.constants.AccountsConstants;
import manhar.laziaf.accounts.services.AccountService;
import manhar.laziaf.accounts.web.dto.CustomerDto;
import manhar.laziaf.accounts.web.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {

    private final AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/get")
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam String mobileNumber) {
        CustomerDto customerDto = accountService.getAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }
}
