package manhar.laziaf.accounts.web.mappers;

import manhar.laziaf.accounts.model.Account;
import manhar.laziaf.accounts.web.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface AccountMapper {

    AccountDto accountToAccountDto(Account account);
    Account accountDtoToAccount(AccountDto accountDto);
}
