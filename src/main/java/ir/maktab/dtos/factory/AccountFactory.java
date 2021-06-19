package ir.maktab.dtos.factory;

import ir.maktab.dtos.AccountDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface AccountFactory {

    static AccountDto createAccount() {
        return new AccountDto().setBalance(0L);
    }
}
