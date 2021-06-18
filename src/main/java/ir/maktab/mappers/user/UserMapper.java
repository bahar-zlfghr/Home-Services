package ir.maktab.mappers.user;

import ir.maktab.data.domain.User;
import ir.maktab.dtos.UserDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface UserMapper {

    User toUser(UserDto userDto);
    UserDto toUserDto(User user);
}
