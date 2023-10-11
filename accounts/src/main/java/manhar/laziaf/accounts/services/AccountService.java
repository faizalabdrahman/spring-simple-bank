package manhar.laziaf.accounts.services;

import manhar.laziaf.accounts.web.dto.AccountDto;
import manhar.laziaf.accounts.web.dto.CustomerDto;

public interface AccountService {

    AccountDto createAccount(CustomerDto customerDto);
    CustomerDto getAccount(String mobileNumber);
    CustomerDto updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
}
