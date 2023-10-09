package manhar.laziaf.accounts.services;

import manhar.laziaf.accounts.web.dto.CustomerDto;

public interface AccountService {

    void createAccount(CustomerDto customerDto);
}
