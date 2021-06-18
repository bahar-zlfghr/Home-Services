package ir.maktab.mappers.person;

import ir.maktab.data.domain.Person;
import ir.maktab.dtos.PersonDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface PersonMapper {

    Person toPerson(PersonDto personDto);
    PersonDto toPersonDto(Person person);
}
