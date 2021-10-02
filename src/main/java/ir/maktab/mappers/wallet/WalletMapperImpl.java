package ir.maktab.mappers.wallet;

import ir.maktab.data.domain.Wallet;
import ir.maktab.dtos.WalletDto;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class WalletMapperImpl implements WalletMapper {

    @Override
    public Wallet toWallet(WalletDto walletDto) {
        if (walletDto != null) {
            return new Wallet()
                    .setId(walletDto.getId())
                    .setBalance(walletDto.getBalance());
        }
        return null;
    }

    @Override
    public WalletDto toWalletDto(Wallet wallet) {
        if (wallet != null) {
            return new WalletDto()
                    .setId(wallet.getId())
                    .setBalance(wallet.getBalance());
        }
        return null;
    }
}
