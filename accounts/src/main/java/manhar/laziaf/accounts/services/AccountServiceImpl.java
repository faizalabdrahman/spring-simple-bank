package manhar.laziaf.accounts.services;

import lombok.extern.slf4j.Slf4j;
import manhar.laziaf.accounts.constants.AccountsConstants;
import manhar.laziaf.accounts.exceptions.CustomerAlreadyExistsException;
import manhar.laziaf.accounts.exceptions.ResourceNotFoundException;
import manhar.laziaf.accounts.model.Account;
import manhar.laziaf.accounts.model.AccountType;
import manhar.laziaf.accounts.model.Customer;
import manhar.laziaf.accounts.repositories.AccountRepository;
import manhar.laziaf.accounts.repositories.CustomerRepository;
import manhar.laziaf.accounts.web.dto.AccountDto;
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
        customer.setCreatedBy("System");
        Customer savedCustomer = customerRepository.save(customer);
        Account savedAccount = accountRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto getAccount(String mobileNumber) {
        Customer fetchedCustomer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account fetchedAccount = accountRepository.findByCustomerId(fetchedCustomer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", fetchedCustomer.getCustomerId().toString())
        );
        CustomerDto customerDto = customerMapper.customerToCustomerDto(fetchedCustomer);
        customerDto.setAccountDto(accountMapper.accountToAccountDto(fetchedAccount));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountDto = customerDto.getAccountDto();
        if (accountDto != null) {
            Account fetchedAccount = accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber", accountDto.getAccountNumber().toString())
            );
            Account account = accountMapper.accountDtoToAccount(accountDto);
            account.setCustomerId(fetchedAccount.getCustomerId());
            accountRepository.save(account);

            Customer fetchedCustomer = customerRepository.findById(account.getCustomerId()).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customerId", account.getCustomerId().toString())
            );
            Customer customer = customerMapper.customerDtoToCustomer(customerDto);
            customer.setCustomerId(fetchedCustomer.getCustomerId());
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    private Account createNewAccount(Customer customer) {
        long accountNumberRandom = 100000000L + new Random().nextInt(900000000);
        Account account = Account.builder()
                .customerId(customer.getCustomerId())
                .accountNumber(accountNumberRandom)
                .accountType(AccountType.SAVINGS)
                .branchAddress(AccountsConstants.BRANCH_ADDRESS)
                .createdBy("system")
                .build();
        return account;
    }
}
