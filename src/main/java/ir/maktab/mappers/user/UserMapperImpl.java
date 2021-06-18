package ir.maktab.mappers.user;

import ir.maktab.data.domain.User;
import ir.maktab.dtos.UserDto;
import ir.maktab.mappers.account.AccountMapper;
import ir.maktab.mappers.person.PersonMapper;

/**
 * @author : Bahar Zolfaghari
 **/
public class UserMapperImpl implements UserMapper {
    private final PersonMapper personMapper;
    private final AccountMapper accountMapper;

    public UserMapperImpl(PersonMapper personMapper, AccountMapper accountMapper) {
        this.personMapper = personMapper;
        this.accountMapper = accountMapper;
    }

    @Override
    public User toUser(UserDto userDto) {
        User user = (User) personMapper.toPerson(userDto);
        return user
                .setStatus(userDto.getStatus())
                .setAccount(accountMapper.toAccount(userDto.getAccountDto()));
    }

    @Override
    public UserDto toUserDto(User user) {
        UserDto userDto = (UserDto) personMapper.toPersonDto(user);
        return userDto
                .setStatus(userDto.getStatus())
                .setAccountDto(accountMapper.toAccountDto(user.getAccount()));
    }
}
