package ir.maktab.mappers.token;

import ir.maktab.data.domain.ConfirmationToken;
import ir.maktab.dtos.ConfirmationTokenDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface ConfirmationTokenMapper {

    ConfirmationToken toConfirmationToken(ConfirmationTokenDto confirmationTokenDto);
    ConfirmationTokenDto toConfirmationTokenDto(ConfirmationToken confirmationToken);
}
