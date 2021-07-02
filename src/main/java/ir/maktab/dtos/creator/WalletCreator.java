package ir.maktab.dtos.creator;

import ir.maktab.dtos.WalletDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface WalletCreator {

    static WalletDto createWallet() {
        return new WalletDto().setBalance(0L);
    }
}
