package ir.maktab.mappers.profilepicture;

import ir.maktab.data.domain.ProfilePicture;
import ir.maktab.dtos.ProfilePictureDto;
import ir.maktab.mappers.specialist.SpecialistMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class ProfilePictureMapperImpl implements ProfilePictureMapper {
    private final SpecialistMapper specialistMapper;

    @Lazy
    public ProfilePictureMapperImpl(SpecialistMapper specialistMapper) {
        this.specialistMapper = specialistMapper;
    }

    @Override
    public ProfilePicture toProfilePicture(ProfilePictureDto profilePictureDto) {
        return new ProfilePicture()
                .setId(profilePictureDto.getId())
                .setName(profilePictureDto.getName())
                .setData(profilePictureDto.getData())
                .setSpecialist(specialistMapper.toSpecialist(profilePictureDto.getSpecialistDto()));
    }

    @Override
    public ProfilePictureDto toProfilePictureDto(ProfilePicture profilePicture) {
        return new ProfilePictureDto()
                .setId(profilePicture.getId())
                .setName(profilePicture.getName())
                .setData(profilePicture.getData())
                .setSpecialistDto(specialistMapper.toSpecialistDto(profilePicture.getSpecialist()));
    }
}
