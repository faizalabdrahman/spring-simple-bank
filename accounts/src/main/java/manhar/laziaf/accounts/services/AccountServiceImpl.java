package manhar.laziaf.accounts.services;

import lombok.extern.slf4j.Slf4j;
import manhar.laziaf.accounts.constants.AccountsConstants;
import manhar.laziaf.accounts.exceptions.CustomerAlreadyExistsException;
import manhar.laziaf.accounts.model.Account;
import manhar.laziaf.accounts.model.AccountType;
import manhar.laziaf.accounts.model.Customer;
import manhar.laziaf.accounts.repositories.AccountRepository;
import manhar.laziaf.accounts.repositories.CustomerRepository;
import manhar.laziaf.accounts.web.dto.CustomerDto;
import manhar.laziaf.accounts.web.mappers.AccountMapper;
import manhar.laziaf.accounts.web.mappers.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;
    private final CustomerMapper customerMapper;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository,
                              AccountMapper accountMapper, CustomerMapper customerMapper) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountMapper = accountMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (customerOptional.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number "
                    + customerDto.getMobileNumber());
        }
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        Account savedAccount = accountRepository.save(createNewAccount(customer));
    }

    private Account createNewAccount(Customer customer) {
        long accountNumberRandom = 1_000_000_000L + new Random().nextInt(900_000_000);
        Account account = Account.builder()
                .customerId(customer.getCustomerId())
                .accountNumber(accountNumberRandom)
                .accountType(AccountType.SAVINGS)
                .branchAddress(AccountsConstants.BRANCH_ADDRESS)
                .build();

        return account;
    }
}
