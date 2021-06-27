package ir.maktab.service.service;

import ir.maktab.dtos.ServiceDto;
import ir.maktab.exceptions.DuplicateServiceNameException;
import ir.maktab.exceptions.NotFoundServiceException;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface ServiceService {

    void saveService(ServiceDto serviceDto) throws DuplicateServiceNameException;
    void UpdateServiceName(Integer id, String name);
    void deleteService(ServiceDto serviceDto);
    ServiceDto getServiceByName(String name) throws NotFoundServiceException;
    Set<ServiceDto> getAllServices();
}
