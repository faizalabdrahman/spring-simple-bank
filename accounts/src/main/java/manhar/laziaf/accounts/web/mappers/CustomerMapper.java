package manhar.laziaf.accounts.web.mappers;

import manhar.laziaf.accounts.model.Customer;
import manhar.laziaf.accounts.web.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDto customerDto);
}
