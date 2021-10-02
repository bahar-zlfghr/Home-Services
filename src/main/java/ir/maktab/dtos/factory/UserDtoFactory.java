package ir.maktab.dtos.factory;

import ir.maktab.data.enums.PersonRole;
import ir.maktab.data.enums.UserStatus;
import ir.maktab.dtos.*;

/**
 * @author : Bahar Zolfaghari
 **/
public interface UserDtoFactory {

    static UserDto createUser(UserDto userDto, PersonRole role) {
        switch (role) {
            case CUSTOMER:
                return (UserDto) new CustomerDto()
                        .setStatus(UserStatus.NEW)
                        .setName(userDto.getName())
                        .setFamily(userDto.getFamily())
                        .setEmail(userDto.getEmail())
                        .setPassword(userDto.getPassword())
                        .setRole(role.getRole());
            case SPECIALIST:
                return (UserDto) new SpecialistDto()
                        .setStatus(UserStatus.NEW)
                        .setName(userDto.getName())
                        .setFamily(userDto.getFamily())
                        .setEmail(userDto.getEmail())
                        .setPassword(userDto.getPassword())
                        .setRole(role.getRole());
            default:
                return new UserDto();
        }
    }
}
