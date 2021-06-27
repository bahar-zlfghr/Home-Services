package ir.maktab.mappers.account;

import ir.maktab.data.domain.Account;
import ir.maktab.dtos.AccountDto;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toAccount(AccountDto accountDto) {
        if (accountDto != null) {
            return new Account()
                    .setId(accountDto.getId())
                    .setBalance(accountDto.getBalance());
        }
        return null;
    }

    @Override
    public AccountDto toAccountDto(Account account) {
        if (account != null) {
            return new AccountDto()
                    .setId(account.getId())
                    .setBalance(account.getBalance());
        }
        return null;
    }
}
