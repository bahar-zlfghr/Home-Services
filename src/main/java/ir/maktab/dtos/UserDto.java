package ir.maktab.dtos;

import ir.maktab.data.enums.UserStatus;

/**
 * @author : Bahar Zolfaghari
 **/
public class UserDto extends PersonDto {
    private UserStatus status;
    private AccountDto accountDto;

    public UserStatus getStatus() {
        return status;
    }

    public UserDto setStatus(UserStatus status) {
        this.status = status;
        return this;
    }

    public AccountDto getAccountDto() {
        return accountDto;
    }

    public UserDto setAccountDto(AccountDto accountDto) {
        this.accountDto = accountDto;
        return this;
    }
}
