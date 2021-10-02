package ir.maktab.mappers.manager;

import ir.maktab.data.domain.Manager;
import ir.maktab.dtos.ManagerDto;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class ManagerMapperImpl implements ManagerMapper {

    @Override
    public Manager toManager(ManagerDto managerDto) {
        if (managerDto != null) {
            return (Manager) new Manager()
                    .setId(managerDto.getId())
                    .setName(managerDto.getName())
                    .setFamily(managerDto.getFamily())
                    .setEmail(managerDto.getEmail())
                    .setPassword(managerDto.getPassword())
                    .setRole(managerDto.getRole());
        }
        return null;
    }

    @Override
    public ManagerDto toManagerDto(Manager manager) {
        if (manager != null) {
            return (ManagerDto) new ManagerDto()
                    .setId(manager.getId())
                    .setName(manager.getName())
                    .setFamily(manager.getFamily())
                    .setEmail(manager.getEmail())
                    .setPassword(manager.getPassword())
                    .setRole(manager.getRole());
        }
        return null;
    }
}
