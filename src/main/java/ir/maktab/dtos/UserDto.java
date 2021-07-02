package ir.maktab.dtos;

import ir.maktab.data.enums.UserStatus;

/**
 * @author : Bahar Zolfaghari
 **/
public class UserDto extends PersonDto {
    private UserStatus status;
    private WalletDto walletDto;
    private ConfirmationTokenDto confirmationTokenDto;

    public UserStatus getStatus() {
        return status;
    }

    public UserDto setStatus(UserStatus status) {
        this.status = status;
        return this;
    }

    public WalletDto getWalletDto() {
        return walletDto;
    }

    public UserDto setWalletDto(WalletDto walletDto) {
        this.walletDto = walletDto;
        return this;
    }

    public ConfirmationTokenDto getConfirmationTokenDto() {
        return confirmationTokenDto;
    }

    public UserDto setConfirmationTokenDto(ConfirmationTokenDto confirmationTokenDto) {
        this.confirmationTokenDto = confirmationTokenDto;
        return this;
    }
}
