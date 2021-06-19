package ir.maktab.service.specialist;

import ir.maktab.dtos.*;
import ir.maktab.exceptions.DuplicateEmailException;
import ir.maktab.exceptions.NotFoundUserException;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface SpecialistService {

    void saveSpecialist(SpecialistDto specialistDto);
    void updateSpecialistSpecialty(Integer id, String specialty);
    void updateSpecialistScore(Integer id, Integer score);
    void updateSpecialistProfilePicture(Integer id, ProfilePictureDto profilePictureDto);
    void updateSpecialistSubServices(Integer id, Set<SubServiceDto> subServiceDtos);
    void updateSpecialistServices(Integer id, Set<ServiceDto> serviceDtos);
    void updateSpecialistOffers(Integer id, Set<OfferDto> offerDtos);
    void updateSpecialistOrders(Integer id, Set<OrderDto> orderDtos);
    void updateSpecialistPassword(String email, String previousPassword, String newPassword);
    void deleteSpecialist(SpecialistDto specialistDto);
    SpecialistDto getSpecialistByEmail(String email) throws NotFoundUserException;
    SpecialistDto getSpecialistByEmailAndPassword(String email, String password) throws NotFoundUserException;
    void checkDuplicateEmail(String email) throws DuplicateEmailException;
}
