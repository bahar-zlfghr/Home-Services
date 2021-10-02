package ir.maktab.service.account;

import ir.maktab.data.domain.Account;
import ir.maktab.data.repository.account.AccountRepository;
import ir.maktab.dtos.AccountDto;
import ir.maktab.exceptions.NotFoundAccountException;
import ir.maktab.mappers.account.AccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public void saveAccount(AccountDto accountDto) {
        accountRepository.save(accountMapper.toAccount(accountDto));
    }

    @Override
    public void updateAccount(AccountDto accountDto) {
        accountRepository.updateAccount(accountDto.getId(), accountDto.getBalance());
    }

    @Override
    public void deleteAccount(AccountDto accountDto) {
        accountRepository.delete(accountMapper.toAccount(accountDto));
    }

    @Override
    public AccountDto getAccountById(Integer id) throws NotFoundAccountException {
        Optional<Account> account = accountRepository.getAccountById(id);
        if (account.isPresent()) {
            return accountMapper.toAccountDto(account.get());
        }
        throw new NotFoundAccountException("Account not found");
    }
}
