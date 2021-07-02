package ir.maktab.mappers.user;

import ir.maktab.data.domain.User;
import ir.maktab.dtos.UserDto;
import ir.maktab.mappers.token.ConfirmationTokenMapper;
import ir.maktab.mappers.wallet.WalletMapper;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class UserMapperImpl implements UserMapper {
    private final WalletMapper walletMapper;
    private final ConfirmationTokenMapper confirmationTokenMapper;

    public UserMapperImpl(WalletMapper walletMapper, ConfirmationTokenMapper confirmationTokenMapper) {
        this.walletMapper = walletMapper;
        this.confirmationTokenMapper = confirmationTokenMapper;
    }

    @Override
    public User toUser(UserDto userDto) {
        if (userDto != null) {
            return (User) new User()
                    .setStatus(userDto.getStatus())
                    .setWallet(walletMapper.toWallet(userDto.getWalletDto()))
                    .setConfirmationToken(confirmationTokenMapper.toConfirmationToken(userDto.getConfirmationTokenDto()))
                    .setId(userDto.getId())
                    .setName(userDto.getName())
                    .setFamily(userDto.getFamily())
                    .setEmail(userDto.getEmail())
                    .setPassword(userDto.getPassword())
                    .setRole(userDto.getRole());
        }
        return null;
    }

    @Override
    public UserDto toUserDto(User user) {
        if (user != null) {
            return (UserDto) new UserDto()
                    .setStatus(user.getStatus())
                    .setWalletDto(walletMapper.toWalletDto(user.getWallet()))
                    .setConfirmationTokenDto(confirmationTokenMapper.toConfirmationTokenDto(user.getConfirmationToken()))
                    .setId(user.getId())
                    .setName(user.getName())
                    .setFamily(user.getFamily())
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword())
                    .setRole(user.getRole());
        }
        return null;
    }
}
