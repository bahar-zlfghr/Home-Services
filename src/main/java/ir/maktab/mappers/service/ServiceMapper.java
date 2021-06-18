package ir.maktab.mappers.service;

import ir.maktab.data.domain.Service;
import ir.maktab.dtos.ServiceDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface ServiceMapper {

    Service toService(ServiceDto serviceDto);
    ServiceDto toServiceDto(Service service);
}
