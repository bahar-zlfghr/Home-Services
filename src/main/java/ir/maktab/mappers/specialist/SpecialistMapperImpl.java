package ir.maktab.mappers.specialist;

import ir.maktab.data.domain.Specialist;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.mappers.profilepicture.ProfilePictureMapper;
import ir.maktab.mappers.user.UserMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class SpecialistMapperImpl implements SpecialistMapper {
    private final UserMapper userMapper;
    private final ProfilePictureMapper profilePictureMapper;

    @Lazy
    public SpecialistMapperImpl(UserMapper userMapper, ProfilePictureMapper profilePictureMapper) {
        this.userMapper = userMapper;
        this.profilePictureMapper = profilePictureMapper;
    }

    @Override
    public Specialist toSpecialist(SpecialistDto specialistDto) {
        Specialist specialist = (Specialist) userMapper.toUser(specialistDto);
        return specialist
                .setSpecialty(specialist.getSpecialty())
                .setScore(specialist.getScore())
                .setProfilePicture(profilePictureMapper.toProfilePicture(specialistDto.getProfilePictureDto()));
    }

    @Override
    public SpecialistDto toSpecialistDto(Specialist specialist) {
        SpecialistDto specialistDto = (SpecialistDto) userMapper.toUserDto(specialist);
        return specialistDto
                .setSpecialty(specialist.getSpecialty())
                .setScore(specialist.getScore())
                .setProfilePictureDto(profilePictureMapper.toProfilePictureDto(specialist.getProfilePicture()));
    }
}
