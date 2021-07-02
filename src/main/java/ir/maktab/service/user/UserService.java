package ir.maktab.service.user;

import ir.maktab.dtos.ConfirmationTokenDto;
import ir.maktab.dtos.UserDto;
import ir.maktab.dtos.filter.UserFilterDto;
import ir.maktab.dtos.filter.UserFilterResult;
import ir.maktab.exceptions.NotFoundUserException;

/**
 * @author : Bahar Zolfaghari
 **/
public interface UserService {

    UserFilterResult filterUsers(UserFilterDto userFilterDto);
    UserDto findUserByConfirmToken(ConfirmationTokenDto confirmationTokenDto) throws NotFoundUserException;
    void updateUserStatus(UserDto userDto);
}
