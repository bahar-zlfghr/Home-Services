package ir.maktab.service.service;

import ir.maktab.data.repository.service.ServiceRepository;
import ir.maktab.dtos.ServiceDto;
import ir.maktab.exceptions.DuplicateServiceNameException;
import ir.maktab.exceptions.NotFoundServiceException;
import ir.maktab.mappers.service.ServiceMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    private final Environment environment;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ServiceMapper serviceMapper, Environment environment) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
        this.environment = environment;
    }

    @Override
    public void saveService(ServiceDto serviceDto) throws DuplicateServiceNameException {
        Optional<ir.maktab.data.domain.Service> serviceByName = serviceRepository.getServiceByName(serviceDto.getName());
        if (!serviceByName.isPresent()) {
            serviceRepository.save(serviceMapper.toService(serviceDto));
        }
        else {
            throw new DuplicateServiceNameException(environment.getProperty("service.name.duplicated"));
        }
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
        throw new NotFoundServiceException(environment.getProperty("service.not.found"));
    }

    @Override
    public Set<ServiceDto> getAllService() {
        return serviceRepository.getAllServices().stream().map(serviceMapper::toServiceDto).collect(Collectors.toSet());
    }
}
