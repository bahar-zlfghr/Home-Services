package ir.maktab.mappers.service;

import ir.maktab.data.domain.Service;
import ir.maktab.dtos.ServiceDto;
import ir.maktab.mappers.specialist.SpecialistMapper;
import ir.maktab.mappers.subservice.SubServiceMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class ServiceMapperImpl implements ServiceMapper {
    private final SubServiceMapper subServiceMapper;
    private final SpecialistMapper specialistMapper;

    @Lazy
    public ServiceMapperImpl(SubServiceMapper subServiceMapper, SpecialistMapper specialistMapper) {
        this.subServiceMapper = subServiceMapper;
        this.specialistMapper = specialistMapper;
    }

    @Override
    public Service toService(ServiceDto serviceDto) {
        return new Service()
                .setId(serviceDto.getId())
                .setName(serviceDto.getName())
                .setSpecialists(serviceDto.getSpecialistDtos().stream().map(specialistMapper::toSpecialist).collect(Collectors.toSet()));
    }

    @Override
    public ServiceDto toServiceDto(Service service) {
        return new ServiceDto()
                .setId(service.getId())
                .setName(service.getName())
                .setSpecialistDtos(service.getSpecialists().stream().map(specialistMapper::toSpecialistDto).collect(Collectors.toSet()));
    }
}
