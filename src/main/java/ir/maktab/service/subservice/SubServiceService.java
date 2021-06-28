package ir.maktab.service.subservice;

import ir.maktab.dtos.ServiceDto;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.dtos.SubServiceDto;
import ir.maktab.exceptions.DuplicateSubServiceNameException;
import ir.maktab.exceptions.NotFoundSubServiceException;
import ir.maktab.exceptions.SubServiceAlreadyProvidedBySpecialistException;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface SubServiceService {

    void saveSubService(SubServiceDto subServiceDto) throws DuplicateSubServiceNameException;
    void updateSubServiceName(Integer id, String name);
    void updateSubServiceBasePrice(Integer id, Long basePrice);
    void updateSubServiceDescription(Integer id, String description);
    void updateSubServiceSpecialists(SubServiceDto subServiceDto, SpecialistDto specialistDto) throws SubServiceAlreadyProvidedBySpecialistException;
    void deleteSubService(SubServiceDto subServiceDto);
    SubServiceDto getSubServiceByName(String name) throws NotFoundSubServiceException;
    Set<SubServiceDto> getSubServicesByService(ServiceDto serviceDto);
    void editSubServiceSpecialists(SubServiceDto subServiceDto, Set<SpecialistDto> specialistDtos);
    void deleteSpecialistFromSubService(SubServiceDto subServiceDto, SpecialistDto specialistDto);
    Set<SubServiceDto> getAllSubServices();
}
