package ir.maktab.service.token;

import ir.maktab.data.domain.ConfirmationToken;
import ir.maktab.data.repository.token.ConfirmationTokenRepository;
import ir.maktab.dtos.ConfirmationTokenDto;
import ir.maktab.exceptions.NotFoundConfirmationTokenException;
import ir.maktab.mappers.token.ConfirmationTokenMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final ConfirmationTokenMapper confirmationTokenMapper;
    private final Environment environment;

    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository, ConfirmationTokenMapper confirmationTokenMapper, Environment environment) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.confirmationTokenMapper = confirmationTokenMapper;
        this.environment = environment;
    }

    @Override
    public void saveConfirmationToken(ConfirmationTokenDto confirmationTokenDto) {
        confirmationTokenRepository.save(confirmationTokenMapper.toConfirmationToken(confirmationTokenDto));
    }

    @Override
    public void updateConfirmationTokenEnabled(ConfirmationTokenDto confirmationTokenDto, boolean enabled) {
        confirmationTokenDto.setEnabled(enabled);
        confirmationTokenRepository.save(confirmationTokenMapper.toConfirmationToken(confirmationTokenDto));
    }

    @Override
    public ConfirmationTokenDto findConfirmationTokenByTokenAndEnabled(String token, boolean enabled) throws NotFoundConfirmationTokenException {
        Optional<ConfirmationToken> byToken = confirmationTokenRepository.findByTokenAndEnabled(token, enabled);
        if (byToken.isPresent()) {
            return confirmationTokenMapper.toConfirmationTokenDto(byToken.get());
        }
        throw new NotFoundConfirmationTokenException(environment.getProperty("token.not.found"));
    }
}
