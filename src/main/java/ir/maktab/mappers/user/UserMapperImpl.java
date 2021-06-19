package ir.maktab.mappers.user;

import ir.maktab.data.domain.User;
import ir.maktab.data.enums.PersonRole;
import ir.maktab.dtos.UserDto;
import ir.maktab.mappers.account.AccountMapper;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class UserMapperImpl implements UserMapper {
    private final AccountMapper accountMapper;

    public UserMapperImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public User toUser(UserDto userDto) {
        return (User) new User()
                .setStatus(userDto.getStatus())
                .setAccount(accountMapper.toAccount(userDto.getAccountDto()))
                .setId(userDto.getId())
                .setName(userDto.getName())
                .setFamily(userDto.getFamily())
                .setEmail(userDto.getEmail())
                .setPassword(userDto.getPassword())
                .setRole(PersonRole.valueOf(userDto.getRole().toUpperCase()));
    }

    @Override
    public UserDto toUserDto(User user) {
        return (UserDto) new UserDto()
                .setStatus(user.getStatus())
                .setAccountDto(accountMapper.toAccountDto(user.getAccount()))
                .setId(user.getId())
                .setName(user.getName())
                .setFamily(user.getFamily())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setRole(user.getRole().getRole());
    }
}
