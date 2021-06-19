package ir.maktab.service.service;

import ir.maktab.data.repository.service.ServiceRepository;
import ir.maktab.dtos.ServiceDto;
import ir.maktab.exceptions.NotFoundServiceException;
import ir.maktab.mappers.service.ServiceMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public void saveService(ServiceDto serviceDto) {
        serviceRepository.save(serviceMapper.toService(serviceDto));
    }

    @Override
    public void UpdateServiceName(Integer id, String name) {
        serviceRepository.UpdateServiceName(id, name);
    }

    @Override
    public void deleteService(ServiceDto serviceDto) {
        serviceRepository.delete(serviceMapper.toService(serviceDto));
    }

    @Override
    public ServiceDto getServiceByName(String name) throws NotFoundServiceException {
        Optional<ir.maktab.data.domain.Service> serviceByName = serviceRepository.getServiceByName(name);
        if (serviceByName.isPresent()) {
            return serviceMapper.toServiceDto(serviceByName.get());
        }
        throw new NotFoundServiceException("service.not.found");
    }
}
