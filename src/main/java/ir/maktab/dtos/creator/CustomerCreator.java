package ir.maktab.dtos.creator;

import ir.maktab.data.enums.PersonRole;
import ir.maktab.data.enums.UserStatus;
import ir.maktab.dtos.ConfirmationTokenDto;
import ir.maktab.dtos.CustomerDto;
import ir.maktab.dtos.WalletDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface CustomerCreator {

    static CustomerDto createCustomerDto(CustomerDto customerDto) {
        ConfirmationTokenDto confirmationTokenDto = ConfirmationTokenCreator.createConfirmationToken();
        WalletDto walletDto = WalletCreator.createWallet();
        return (CustomerDto) customerDto
                .setStatus(UserStatus.NEW)
                .setWalletDto(walletDto)
                .setConfirmationTokenDto(confirmationTokenDto)
                .setRole(PersonRole.CUSTOMER);
    }
}
