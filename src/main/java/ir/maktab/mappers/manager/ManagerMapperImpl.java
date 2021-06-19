package ir.maktab.mappers.manager;

import ir.maktab.data.domain.Manager;
import ir.maktab.dtos.ManagerDto;
import ir.maktab.mappers.person.PersonMapper;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class ManagerMapperImpl implements ManagerMapper {
    private final PersonMapper personMapper;

    public ManagerMapperImpl(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Override
    public Manager toManager(ManagerDto managerDto) {
        return (Manager) personMapper.toPerson(managerDto);
    }

    @Override
    public ManagerDto toManagerDto(Manager manager) {
        return (ManagerDto) personMapper.toPersonDto(manager);
    }
}
