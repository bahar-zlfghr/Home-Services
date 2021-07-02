package ir.maktab.service.person;

import ir.maktab.data.domain.Person;
import ir.maktab.data.repository.person.PersonRepository;
import ir.maktab.dtos.PersonDto;
import ir.maktab.exceptions.NotFoundUserException;
import ir.maktab.exceptions.PasswordNotMatchedException;
import ir.maktab.mappers.person.PersonMapper;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final PasswordEncoder passwordEncoder;
    private final Environment environment;

    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper, PasswordEncoder passwordEncoder, Environment environment) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
    }

    @Override
    public PersonDto findPersonByEmail(String email) {
        return personMapper.toPersonDto(personRepository.findByEmail(email).get());
    }

    @Override
    public void updatePersonPassword(String email, String previousPassword, String newPassword) throws NotFoundUserException, PasswordNotMatchedException {
        Optional<Person> byEmail = personRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            Person person = byEmail.get();
            if (passwordEncoder.matches(previousPassword, person.getPassword())) {
                person.setPassword(passwordEncoder.encode(newPassword));
                personRepository.save(person);
            }
            else {
                throw new PasswordNotMatchedException(environment.getProperty("person.password.not.matched"));
            }
        }
        else {
            throw new NotFoundUserException(environment.getRequiredProperty("customer.not.found"));
        }
    }
}
