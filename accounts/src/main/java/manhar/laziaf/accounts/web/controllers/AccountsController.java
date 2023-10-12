package manhar.laziaf.accounts.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(
        name = "CRUD REST APIs for Accounts in Simple Bank",
        description = "CRUD REST APIs in Simple Bank to CREATE, UPDATE, FETCH, DELETE account details"
)
@RestController
@RequestMapping(path = "/api/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    private final AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new customer and account in Simple Bank"
    )
    @ApiResponse(
            responseCode = "201",
            description =  "HTTP status created"
    )
    @PostMapping("/create")
    public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid CustomerDto customerDto) {
        return new ResponseEntity<>(accountService.createAccount(customerDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Account Details REST API",
            description = "REST API to get customer and account details based on mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description =  "HTTP status ok"
    )
    @GetMapping("/get")
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam
                                                             @Pattern(regexp = "(^$|[0-9]{8})", message = "Mobile Number must be numeric")
                                                             String mobileNumber) {
        return new ResponseEntity<>(accountService.getAccount(mobileNumber), HttpStatus.OK);
    }

    @Operation(
            summary = "Update Account REST API",
            description = "REST API to update customer and account based on account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description =  "HTTP status ok"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description =  "HTTP status internal server error"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody @Valid CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = accountService.updateAccount(customerDto);
        if (updatedCustomerDto != null) {
            return new ResponseEntity<>(new ResponseDto(AccountsConstants.MESSAGE_200), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDto(AccountsConstants.MESSAGE_500), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(
            summary = "Delete Account REST API",
            description = "REST API to delete customer and account base on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description =  "HTTP status ok"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description =  "HTTP status internal server error"
            )
    })
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
