package ir.maktab.mappers.subservice;

import ir.maktab.data.domain.SubService;
import ir.maktab.dtos.SubServiceDto;
import ir.maktab.mappers.service.ServiceMapper;
import ir.maktab.mappers.specialist.SpecialistMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class SubServiceMapperImpl implements SubServiceMapper {
    private final SpecialistMapper specialistMapper;
    private final ServiceMapper serviceMapper;

    @Lazy
    public SubServiceMapperImpl(SpecialistMapper specialistMapper, ServiceMapper serviceMapper) {
        this.specialistMapper = specialistMapper;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public SubService toSubService(SubServiceDto subServiceDto) {
        if (subServiceDto != null) {
            return new SubService()
                    .setId(subServiceDto.getId())
                    .setName(subServiceDto.getName())
                    .setBasePrice(subServiceDto.getBasePrice())
                    .setDescription(subServiceDto.getDescription())
                    .setService(serviceMapper.toService(subServiceDto.getServiceDto()))
                    .setSpecialists(subServiceDto.getSpecialistDtos().stream().map(specialistMapper::toSpecialist).collect(Collectors.toSet()));
        }
        return null;
    }

    @Override
    public SubServiceDto toSubServiceDto(SubService subService) {
        if (subService != null) {
            return new SubServiceDto()
                    .setId(subService.getId())
                    .setName(subService.getName())
                    .setBasePrice(subService.getBasePrice())
                    .setDescription(subService.getDescription())
                    .setServiceDto(serviceMapper.toServiceDto(subService.getService()))
                    .setSpecialistDtos(subService.getSpecialists().stream().map(specialistMapper::toSpecialistDto).collect(Collectors.toSet()));
        }
        return null;
    }
}
