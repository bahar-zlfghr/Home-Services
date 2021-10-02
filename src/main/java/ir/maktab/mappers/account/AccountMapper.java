package ir.maktab.mappers.account;

import ir.maktab.data.domain.Account;
import ir.maktab.dtos.AccountDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface AccountMapper {

    Account toAccount(AccountDto accountDto);
    AccountDto toAccountDto(Account account);
}
