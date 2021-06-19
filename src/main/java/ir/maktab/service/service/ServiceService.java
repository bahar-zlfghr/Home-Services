package ir.maktab.service.service;

import ir.maktab.dtos.ServiceDto;
import ir.maktab.exceptions.NotFoundServiceException;

/**
 * @author : Bahar Zolfaghari
 **/
public interface ServiceService {

    void saveService(ServiceDto serviceDto);
    void UpdateServiceName(Integer id, String name);
    void deleteService(ServiceDto serviceDto);
    ServiceDto getServiceByName(String name) throws NotFoundServiceException;
}
