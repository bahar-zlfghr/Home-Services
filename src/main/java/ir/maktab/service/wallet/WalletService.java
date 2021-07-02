package ir.maktab.service.wallet;

import ir.maktab.dtos.WalletDto;
import ir.maktab.exceptions.NotFoundAccountException;

/**
 * @author : Bahar Zolfaghari
 **/
public interface WalletService {

    void saveWallet(WalletDto walletDto);
    void updateWallet(WalletDto walletDto);
    void deleteWallet(WalletDto walletDto);

    //ToDo: get wallet by customer and specialist (user)
    WalletDto getWalletById(Integer id) throws NotFoundAccountException;
}
