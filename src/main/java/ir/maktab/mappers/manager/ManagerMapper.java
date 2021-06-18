package ir.maktab.mappers.manager;

import ir.maktab.data.domain.Manager;
import ir.maktab.dtos.ManagerDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface ManagerMapper {

    Manager toManager(ManagerDto managerDto);
    ManagerDto toManagerDto(Manager manager);
}
