package ir.maktab.mappers.profilepicture;

import ir.maktab.data.domain.ProfilePicture;
import ir.maktab.dtos.ProfilePictureDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface ProfilePictureMapper {

    ProfilePicture toProfilePicture(ProfilePictureDto profilePictureDto);
    ProfilePictureDto toProfilePictureDto(ProfilePicture profilePicture);
}
