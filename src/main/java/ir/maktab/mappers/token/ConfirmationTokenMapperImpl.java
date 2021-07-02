package ir.maktab.mappers.token;

import ir.maktab.data.domain.ConfirmationToken;
import ir.maktab.dtos.ConfirmationTokenDto;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class ConfirmationTokenMapperImpl implements ConfirmationTokenMapper {

    @Override
    public ConfirmationToken toConfirmationToken(ConfirmationTokenDto confirmationTokenDto) {
        return new ConfirmationToken()
                .setId(confirmationTokenDto.getId())
                .setToken(confirmationTokenDto.getToken())
                .setCreatedDate(confirmationTokenDto.getCreatedDate())
                .setEnabled(confirmationTokenDto.getEnabled());
    }

    @Override
    public ConfirmationTokenDto toConfirmationTokenDto(ConfirmationToken confirmationToken) {
        return new ConfirmationTokenDto()
                .setId(confirmationToken.getId())
                .setToken(confirmationToken.getToken())
                .setCreatedDate(confirmationToken.getCreatedDate())
                .setEnabled(confirmationToken.getEnabled());
    }
}
