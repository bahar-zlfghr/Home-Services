package ir.maktab.service.token;

import ir.maktab.dtos.ConfirmationTokenDto;
import ir.maktab.exceptions.NotFoundConfirmationTokenException;

/**
 * @author : Bahar Zolfaghari
 **/
public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationTokenDto confirmationTokenDto);
    void updateConfirmationTokenEnabled(ConfirmationTokenDto confirmationTokenDto, boolean enabled);
    ConfirmationTokenDto findConfirmationTokenByTokenAndEnabled(String token, boolean enabled) throws NotFoundConfirmationTokenException;
}
