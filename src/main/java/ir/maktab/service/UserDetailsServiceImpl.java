package ir.maktab.service;

import ir.maktab.data.domain.Person;
import ir.maktab.data.repository.person.PersonRepository;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Service("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonRepository personRepository;
    private final Environment environment;

    public UserDetailsServiceImpl(PersonRepository personRepository, Environment environment) {
        this.personRepository = personRepository;
        this.environment = environment;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> byEmail = personRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            Person person = byEmail.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(person.getEmail())
                    .password(person.getPassword())
                    .roles(person.getRole().getRole().toUpperCase()).build();
        }
        throw new UsernameNotFoundException(environment.getProperty("user.not.login"));
    }
}
