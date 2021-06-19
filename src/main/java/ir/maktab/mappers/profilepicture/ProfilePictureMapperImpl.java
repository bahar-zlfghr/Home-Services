package ir.maktab.mappers.profilepicture;

import ir.maktab.data.domain.ProfilePicture;
import ir.maktab.dtos.ProfilePictureDto;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class ProfilePictureMapperImpl implements ProfilePictureMapper {

    @Override
    public ProfilePicture toProfilePicture(ProfilePictureDto profilePictureDto) {
        return new ProfilePicture()
                .setId(profilePictureDto.getId())
                .setName(profilePictureDto.getName())
                .setData(profilePictureDto.getData());
    }

    @Override
    public ProfilePictureDto toProfilePictureDto(ProfilePicture profilePicture) {
        return new ProfilePictureDto()
                .setId(profilePicture.getId())
                .setName(profilePicture.getName())
                .setData(profilePicture.getData());
    }
}
