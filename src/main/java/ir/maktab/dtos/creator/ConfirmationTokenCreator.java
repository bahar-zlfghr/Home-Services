package ir.maktab.dtos.creator;

import ir.maktab.dtos.ConfirmationTokenDto;

import java.util.UUID;

/**
 * @author : Bahar Zolfaghari
 **/
public interface ConfirmationTokenCreator {

    static ConfirmationTokenDto createConfirmationToken() {
        return new ConfirmationTokenDto()
                .setToken(UUID.randomUUID().toString())
                .setEnabled(true);
    }
}
