package ir.maktab.dtos.creator;

import ir.maktab.dtos.ConfirmationTokenDto;
import ir.maktab.dtos.UserDto;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author : Bahar Zolfaghari
 **/
public interface MailCreator {

    static SimpleMailMessage createEmail(UserDto userDto, ConfirmationTokenDto confirmationTokenDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userDto.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("home.service123123@gmail.com");
        mailMessage.setText("To confirm your account, please click here: " +
                "http://localhost:8080/confirm-account?token=" + confirmationTokenDto.getToken());
        return mailMessage;
    }
}
