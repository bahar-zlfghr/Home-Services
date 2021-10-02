package ir.maktab.dtos.factory;

import ir.maktab.dtos.ProfilePictureDto;
import ir.maktab.dtos.SpecialistDto;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author : Bahar Zolfaghari
 **/
public interface ProfilePictureFactory {

    static ProfilePictureDto createProfilePicture(CommonsMultipartFile profilePicture, SpecialistDto specialistDto) {
        return new ProfilePictureDto()
                .setName(profilePicture.getName())
                .setData(profilePicture.getBytes())
                .setSpecialistDto(specialistDto);
    }
}
