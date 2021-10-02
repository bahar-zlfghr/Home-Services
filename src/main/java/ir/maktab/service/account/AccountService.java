package ir.maktab.service.account;

import ir.maktab.dtos.AccountDto;
import ir.maktab.exceptions.NotFoundAccountException;

/**
 * @author : Bahar Zolfaghari
 **/
public interface AccountService {

    void saveAccount(AccountDto accountDto);
    void updateAccount(AccountDto accountDto);
    void deleteAccount(AccountDto accountDto);
    AccountDto getAccountById(Integer id) throws NotFoundAccountException;
}
