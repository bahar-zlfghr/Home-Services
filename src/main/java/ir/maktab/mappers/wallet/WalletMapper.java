package ir.maktab.mappers.wallet;

import ir.maktab.data.domain.Wallet;
import ir.maktab.dtos.WalletDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface WalletMapper {

    Wallet toWallet(WalletDto walletDto);
    WalletDto toWalletDto(Wallet wallet);
}
