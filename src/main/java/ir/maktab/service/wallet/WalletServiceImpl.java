package ir.maktab.service.wallet;

import ir.maktab.data.domain.Wallet;
import ir.maktab.data.repository.account.AccountRepository;
import ir.maktab.dtos.WalletDto;
import ir.maktab.exceptions.NotFoundAccountException;
import ir.maktab.mappers.wallet.WalletMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class WalletServiceImpl implements WalletService {
    private final AccountRepository accountRepository;
    private final WalletMapper walletMapper;

    public WalletServiceImpl(AccountRepository accountRepository, WalletMapper walletMapper) {
        this.accountRepository = accountRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    public void saveWallet(WalletDto walletDto) {
        accountRepository.save(walletMapper.toWallet(walletDto));
    }

    @Override
    public void updateWallet(WalletDto walletDto) {
        accountRepository.updateAccount(walletDto.getId(), walletDto.getBalance());
    }

    @Override
    public void deleteWallet(WalletDto walletDto) {
        accountRepository.delete(walletMapper.toWallet(walletDto));
    }

    @Override
    public WalletDto getWalletById(Integer id) throws NotFoundAccountException {
        Optional<Wallet> account = accountRepository.getAccountById(id);
        if (account.isPresent()) {
            return walletMapper.toWalletDto(account.get());
        }
        throw new NotFoundAccountException("Account not found");
    }
}
