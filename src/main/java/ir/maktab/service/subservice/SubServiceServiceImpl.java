package ir.maktab.service.subservice;

import ir.maktab.data.domain.SubService;
import ir.maktab.data.repository.subservice.SubServiceRepository;
import ir.maktab.dtos.ServiceDto;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.dtos.SubServiceDto;
import ir.maktab.exceptions.DuplicateSubServiceNameException;
import ir.maktab.exceptions.NotFoundSubServiceException;
import ir.maktab.mappers.service.ServiceMapper;
import ir.maktab.mappers.specialist.SpecialistMapper;
import ir.maktab.mappers.subservice.SubServiceMapper;
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
public class SubServiceServiceImpl implements SubServiceService {
    private final SubServiceRepository subServiceRepository;
    private final SubServiceMapper subServiceMapper;
    private final ServiceMapper serviceMapper;
    private final SpecialistMapper specialistMapper;
    private final Environment environment;

    public SubServiceServiceImpl(SubServiceRepository subServiceRepository, SubServiceMapper subServiceMapper, ServiceMapper serviceMapper, SpecialistMapper specialistMapper, Environment environment) {
        this.subServiceRepository = subServiceRepository;
        this.subServiceMapper = subServiceMapper;
        this.serviceMapper = serviceMapper;
        this.specialistMapper = specialistMapper;
        this.environment = environment;
    }

    @Override
    public void saveSubService(SubServiceDto subServiceDto) throws DuplicateSubServiceNameException {
        Optional<SubService> subServiceByName = subServiceRepository.getSubServiceByName(subServiceDto.getName());
        if (!subServiceByName.isPresent()) {
            subServiceRepository.save(subServiceMapper.toSubService(subServiceDto));
        }
        else {
            throw new DuplicateSubServiceNameException(environment.getProperty("sub.service.name.duplicated"));
        }
    }

    @Override
    public void updateSubServiceName(Integer id, String name) {
        subServiceRepository.updateSubServiceName(id, name);
    }

    @Override
    public void updateSubServiceBasePrice(Integer id, Long basePrice) {
        subServiceRepository.updateSubServiceBasePrice(id, basePrice);
    }

    @Override
    public void updateSubServiceDescription(Integer id, String description) {
        subServiceRepository.updateSubServiceDescription(id, description);
    }

    @Override
    public void updateSubServiceSpecialists(SubServiceDto subServiceDto) {
        subServiceRepository.save(subServiceMapper.toSubService(subServiceDto));
    }

    @Override
    public void deleteSubService(SubServiceDto subServiceDto) {
        subServiceRepository.delete(subServiceMapper.toSubService(subServiceDto));
    }

    @Override
    public SubServiceDto getSubServiceByName(String name) throws NotFoundSubServiceException {
        Optional<SubService> subServiceByName = subServiceRepository.getSubServiceByName(name);
        if (subServiceByName.isPresent()) {
            return subServiceMapper.toSubServiceDto(subServiceByName.get());
        }
        throw new NotFoundSubServiceException(environment.getProperty("sub.service.not.found"));
    }

    @Override
    public Set<SubServiceDto> getSubServicesByService(ServiceDto serviceDto) {
        return subServiceRepository.getSubServicesByService(serviceMapper.toService(serviceDto))
                .stream().map(subServiceMapper::toSubServiceDto).collect(Collectors.toSet());
    }

    @Override
    public void assignSpecialistToSubService(SubServiceDto subServiceDto, SpecialistDto specialistDto) {
        subServiceDto.getSpecialistDtos().add(specialistDto);
        updateSubServiceSpecialists(subServiceDto);
    }

    @Override
    public void editSubServiceSpecialists(SubServiceDto subServiceDto, Set<SpecialistDto> specialistDtos) {
        updateSubServiceSpecialists(subServiceDto);
    }

    @Override
    public void deleteSpecialistFromSubService(SubServiceDto subServiceDto, SpecialistDto specialistDto) {
        subServiceDto.getSpecialistDtos().remove(specialistDto);
        updateSubServiceSpecialists(subServiceDto);
    }

    @Override
    public Set<SubServiceDto> getAllSubServices() {
        return subServiceRepository.getAllSubServices().stream().map(subServiceMapper::toSubServiceDto).collect(Collectors.toSet());
    }
}
