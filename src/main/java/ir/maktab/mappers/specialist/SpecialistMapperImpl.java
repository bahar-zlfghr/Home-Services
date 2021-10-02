package ir.maktab.mappers.specialist;

import ir.maktab.data.domain.Specialist;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.mappers.token.ConfirmationTokenMapper;
import ir.maktab.mappers.wallet.WalletMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class SpecialistMapperImpl implements SpecialistMapper {
    private final WalletMapper walletMapper;
    private final ConfirmationTokenMapper confirmationTokenMapper;

    @Lazy
    public SpecialistMapperImpl(WalletMapper walletMapper, ConfirmationTokenMapper confirmationTokenMapper) {
        this.walletMapper = walletMapper;
        this.confirmationTokenMapper = confirmationTokenMapper;
    }

    @Override
    public Specialist toSpecialist(SpecialistDto specialistDto) {
        if (specialistDto != null) {
            return (Specialist) new Specialist()
                    .setScore(specialistDto.getScore())
                    .setProfilePicture(specialistDto.getProfilePicture())
                    .setStatus(specialistDto.getStatus())
                    .setWallet(walletMapper.toWallet(specialistDto.getWalletDto()))
                    .setConfirmationToken(confirmationTokenMapper.toConfirmationToken(specialistDto.getConfirmationTokenDto()))
                    .setId(specialistDto.getId())
                    .setName(specialistDto.getName())
                    .setFamily(specialistDto.getFamily())
                    .setEmail(specialistDto.getEmail())
                    .setPassword(specialistDto.getPassword())
                    .setRole(specialistDto.getRole());
        }
        return null;
    }

    @Override
    public SpecialistDto toSpecialistDto(Specialist specialist) {
        if (specialist != null) {
        return (SpecialistDto) new SpecialistDto()
                .setScore(specialist.getScore())
                .setProfilePicture(specialist.getProfilePicture())
                .setStatus(specialist.getStatus())
                .setWalletDto(walletMapper.toWalletDto(specialist.getWallet()))
                .setConfirmationTokenDto(confirmationTokenMapper.toConfirmationTokenDto(specialist.getConfirmationToken()))
                .setId(specialist.getId())
                .setName(specialist.getName())
                .setFamily(specialist.getFamily())
                .setEmail(specialist.getEmail())
                .setPassword(specialist.getPassword())
                .setRole(specialist.getRole());
        }
        else {
            return null;
        }
    }
}
