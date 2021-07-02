package ir.maktab.dtos.creator;

import ir.maktab.data.enums.PersonRole;
import ir.maktab.data.enums.UserStatus;
import ir.maktab.dtos.ConfirmationTokenDto;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.dtos.WalletDto;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author : Bahar Zolfaghari
 **/
public interface SpecialistCreator {

    static SpecialistDto createSpecialist(SpecialistDto specialistDto, CommonsMultipartFile picture) {
        WalletDto walletDto = WalletCreator.createWallet();
        ConfirmationTokenDto confirmationTokenDto = ConfirmationTokenCreator.createConfirmationToken();
        return (SpecialistDto) specialistDto
                .setScore(0)
                .setProfilePicture(picture.getBytes())
                .setStatus(UserStatus.NEW)
                .setWalletDto(walletDto)
                .setConfirmationTokenDto(confirmationTokenDto)
                .setRole(PersonRole.SPECIALIST);
    }
}
