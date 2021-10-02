package ir.maktab.service.service;

import ir.maktab.dtos.ServiceDto;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.exceptions.DuplicateServiceNameException;
import ir.maktab.exceptions.NotFoundServiceException;
import ir.maktab.exceptions.ServiceAlreadyProvidedBySpecialistException;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface ServiceService {

    void saveService(ServiceDto serviceDto) throws DuplicateServiceNameException;
    void updateServiceSpecialists(ServiceDto serviceDto, SpecialistDto specialistDto) throws ServiceAlreadyProvidedBySpecialistException;
    void UpdateServiceName(Integer id, String name);
    void deleteService(ServiceDto serviceDto);
    ServiceDto getServiceByName(String name) throws NotFoundServiceException;
    Set<ServiceDto> getAllServices();
}
