package ir.maktab.service.mail;

import org.springframework.mail.SimpleMailMessage;

/**
 * @author : Bahar Zolfaghari
 **/
public interface EmailSenderService {

    void sendEmail(SimpleMailMessage email);
}
