package ir.maktab.service.subservice;

import ir.maktab.data.domain.SubService;
import ir.maktab.data.repository.subservice.SubServiceRepository;
import ir.maktab.dtos.ServiceDto;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.dtos.SubServiceDto;
import ir.maktab.exceptions.NotFoundSubServiceException;
import ir.maktab.mappers.service.ServiceMapper;
import ir.maktab.mappers.specialist.SpecialistMapper;
import ir.maktab.mappers.subservice.SubServiceMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
public class SubServiceServiceImpl implements SubServiceService {
    private final SubServiceRepository subServiceRepository;
    private final SubServiceMapper subServiceMapper;
    private final ServiceMapper serviceMapper;
    private final SpecialistMapper specialistMapper;

    public SubServiceServiceImpl(SubServiceRepository subServiceRepository, SubServiceMapper subServiceMapper, ServiceMapper serviceMapper, SpecialistMapper specialistMapper) {
        this.subServiceRepository = subServiceRepository;
        this.subServiceMapper = subServiceMapper;
        this.serviceMapper = serviceMapper;
        this.specialistMapper = specialistMapper;
    }

    @Override
    public void saveSubService(SubServiceDto subServiceDto) {
        subServiceRepository.save(subServiceMapper.toSubService(subServiceDto));
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
    public void updateSubServiceSpecialists(Integer id, Set<SpecialistDto> specialistDtos) {
        subServiceRepository.updateSubServiceSpecialists(id, specialistDtos.stream().map(specialistMapper::toSpecialist).collect(Collectors.toSet()));
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
        throw new NotFoundSubServiceException("sub.service.not.found");
    }

    @Override
    public Set<SubServiceDto> getSubServicesByService(ServiceDto serviceDto) {
        return subServiceRepository.getSubServicesByService(serviceMapper.toService(serviceDto))
                .stream().map(subServiceMapper::toSubServiceDto).collect(Collectors.toSet());
    }

    @Override
    public void assignSpecialistToSubService(SubServiceDto subServiceDto, SpecialistDto specialistDto) {
        subServiceDto.getSpecialistDtos().add(specialistDto);
        updateSubServiceSpecialists(subServiceDto.getId(), subServiceDto.getSpecialistDtos());
    }

    @Override
    public void editSubServiceSpecialists(SubServiceDto subServiceDto, Set<SpecialistDto> specialistDtos) {
        updateSubServiceSpecialists(subServiceDto.getId(), specialistDtos);
    }

    @Override
    public void deleteSpecialistFromSubService(SubServiceDto subServiceDto, SpecialistDto specialistDto) {
        subServiceDto.getSpecialistDtos().remove(specialistDto);
        updateSubServiceSpecialists(subServiceDto.getId(), subServiceDto.getSpecialistDtos());
    }
}
