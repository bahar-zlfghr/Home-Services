package ir.maktab.mappers.specialist;

import ir.maktab.data.domain.Specialist;
import ir.maktab.data.enums.PersonRole;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.mappers.account.AccountMapper;
import ir.maktab.mappers.profilepicture.ProfilePictureMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class SpecialistMapperImpl implements SpecialistMapper {
    private final ProfilePictureMapper profilePictureMapper;
    private final AccountMapper accountMapper;

    @Lazy
    public SpecialistMapperImpl(ProfilePictureMapper profilePictureMapper, AccountMapper accountMapper) {
        this.profilePictureMapper = profilePictureMapper;
        this.accountMapper = accountMapper;
    }

    @Override
    public Specialist toSpecialist(SpecialistDto specialistDto) {
        return (Specialist) new Specialist()
                .setSpecialty(specialistDto.getSpecialty())
                .setScore(specialistDto.getScore())
                .setProfilePicture(profilePictureMapper.toProfilePicture(specialistDto.getProfilePictureDto()))
                .setStatus(specialistDto.getStatus())
                .setAccount(accountMapper.toAccount(specialistDto.getAccountDto()))
                .setId(specialistDto.getId())
                .setName(specialistDto.getName())
                .setFamily(specialistDto.getFamily())
                .setEmail(specialistDto.getEmail())
                .setPassword(specialistDto.getPassword())
                .setRole(PersonRole.valueOf(specialistDto.getRole().toUpperCase()));
    }

    @Override
    public SpecialistDto toSpecialistDto(Specialist specialist) {
        return (SpecialistDto) new SpecialistDto()
                .setSpecialty(specialist.getSpecialty())
                .setScore(specialist.getScore())
                .setProfilePictureDto(profilePictureMapper.toProfilePictureDto(specialist.getProfilePicture()))
                .setStatus(specialist.getStatus())
                .setAccountDto(accountMapper.toAccountDto(specialist.getAccount()))
                .setId(specialist.getId())
                .setName(specialist.getName())
                .setFamily(specialist.getFamily())
                .setEmail(specialist.getEmail())
                .setPassword(specialist.getPassword())
                .setRole(specialist.getRole().getRole());
    }
}
