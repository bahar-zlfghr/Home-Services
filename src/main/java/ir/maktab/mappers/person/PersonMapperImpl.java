package ir.maktab.mappers.person;

import ir.maktab.data.domain.Person;
import ir.maktab.dtos.PersonDto;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public Person toPerson(PersonDto personDto) {
        if (personDto != null) {
            return new Person()
                    .setId(personDto.getId())
                    .setName(personDto.getName())
                    .setFamily(personDto.getFamily())
                    .setEmail(personDto.getEmail())
                    .setPassword(personDto.getPassword())
                    .setRole(personDto.getRole());
        }
        return null;
    }

    @Override
    public PersonDto toPersonDto(Person person) {
        if (person != null) {
            return new PersonDto()
                    .setId(person.getId())
                    .setName(person.getName())
                    .setFamily(person.getFamily())
                    .setEmail(person.getEmail())
                    .setPassword(person.getPassword())
                    .setRole(person.getRole());
        }
        return null;
    }
}
