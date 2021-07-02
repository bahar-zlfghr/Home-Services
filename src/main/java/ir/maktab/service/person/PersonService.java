package ir.maktab.service.person;

import ir.maktab.dtos.PersonDto;
import ir.maktab.exceptions.NotFoundUserException;
import ir.maktab.exceptions.PasswordNotMatchedException;

/**
 * @author : Bahar Zolfaghari
 **/
public interface PersonService {

    PersonDto findPersonByEmail(String email);
    void updatePersonPassword(String email, String previousPassword, String newPassword) throws NotFoundUserException, PasswordNotMatchedException;
}
