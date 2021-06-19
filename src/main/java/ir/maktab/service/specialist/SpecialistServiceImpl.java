package ir.maktab.service.specialist;

import ir.maktab.data.domain.Specialist;
import ir.maktab.data.repository.specialist.SpecialistRepository;
import ir.maktab.dtos.*;
import ir.maktab.exceptions.DuplicateEmailException;
import ir.maktab.exceptions.NotEmptyProfilePictureException;
import ir.maktab.exceptions.NotEmptySpecialtyException;
import ir.maktab.exceptions.NotFoundUserException;
import ir.maktab.mappers.offer.OfferMapper;
import ir.maktab.mappers.order.OrderMapper;
import ir.maktab.mappers.profilepicture.ProfilePictureMapper;
import ir.maktab.mappers.service.ServiceMapper;
import ir.maktab.mappers.specialist.SpecialistMapper;
import ir.maktab.mappers.subservice.SubServiceMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
public class SpecialistServiceImpl implements SpecialistService {
    private final SpecialistRepository specialistRepository;
    private final SpecialistMapper specialistMapper;
    private final ProfilePictureMapper profilePictureMapper;
    private final SubServiceMapper subServiceMapper;
    private final ServiceMapper serviceMapper;
    private final OfferMapper offerMapper;
    private final OrderMapper orderMapper;
    private final PasswordEncoder passwordEncoder;

    public SpecialistServiceImpl(SpecialistRepository specialistRepository, SpecialistMapper specialistMapper, ProfilePictureMapper profilePictureMapper, SubServiceMapper subServiceMapper, ServiceMapper serviceMapper, OfferMapper offerMapper, OrderMapper orderMapper, PasswordEncoder passwordEncoder) {
        this.specialistRepository = specialistRepository;
        this.specialistMapper = specialistMapper;
        this.profilePictureMapper = profilePictureMapper;
        this.subServiceMapper = subServiceMapper;
        this.serviceMapper = serviceMapper;
        this.offerMapper = offerMapper;
        this.orderMapper = orderMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveSpecialist(SpecialistDto specialistDto) throws NotEmptySpecialtyException, NotEmptyProfilePictureException {
        if (specialistDto.getSpecialty().equals("") || specialistDto.getSpecialty() == null) {
            throw new NotEmptySpecialtyException("{specialist.specialty.not.empty}");
        }
        if (specialistDto.getProfilePictureDto().getData().length == 0) {
            throw new NotEmptyProfilePictureException("{specialist.profile.picture.not.empty}");
        }

        String password = specialistDto.getPassword();
        specialistDto.setPassword(passwordEncoder.encode(password));

        specialistRepository.save(specialistMapper.toSpecialist(specialistDto));
    }

    @Override
    public void updateSpecialistSpecialty(Integer id, String specialty) {
        specialistRepository.updateSpecialistSpecialty(id, specialty);
    }

    @Override
    public void updateSpecialistScore(Integer id, Integer score) {
        specialistRepository.updateSpecialistScore(id, score);
    }

    @Override
    public void updateSpecialistProfilePicture(Integer id, ProfilePictureDto profilePictureDto) {
        specialistRepository.updateSpecialistProfilePicture(id, profilePictureMapper.toProfilePicture(profilePictureDto));
    }

    @Override
    public void updateSpecialistSubServices(Integer id, Set<SubServiceDto> subServiceDtos) {
        specialistRepository.updateSpecialistSubServices(id, subServiceDtos.stream().map(subServiceMapper::toSubService).collect(Collectors.toSet()));
    }

    @Override
    public void updateSpecialistServices(Integer id, Set<ServiceDto> serviceDtos) {
        specialistRepository.updateSpecialistServices(id, serviceDtos.stream().map(serviceMapper::toService).collect(Collectors.toSet()));
    }

    @Override
    public void updateSpecialistOffers(Integer id, Set<OfferDto> offerDtos) {
        specialistRepository.updateSpecialistOffers(id, offerDtos.stream().map(offerMapper::toOffer).collect(Collectors.toSet()));
    }

    @Override
    public void updateSpecialistOrders(Integer id, Set<OrderDto> orderDtos) {
        specialistRepository.updateSpecialistOrders(id, orderDtos.stream().map(orderMapper::toOrder).collect(Collectors.toSet()));
    }

    @Override
    public void updateSpecialistPassword(String email, String previousPassword, String newPassword) {
        specialistRepository.updateSpecialistPassword(email, previousPassword, newPassword);
    }

    @Override
    public void deleteSpecialist(SpecialistDto specialistDto) {
        specialistRepository.delete(specialistMapper.toSpecialist(specialistDto));
    }

    @Override
    public SpecialistDto getSpecialistByEmail(String email) throws NotFoundUserException {
        Optional<Specialist> specialistByEmail = specialistRepository.getSpecialistByEmail(email);
        if (specialistByEmail.isPresent()) {
            return specialistMapper.toSpecialistDto(specialistByEmail.get());
        }
        throw new NotFoundUserException("{specialist.not.found}");
    }

    @Override
    public SpecialistDto getSpecialistByEmailAndPassword(String email, String password) throws NotFoundUserException {
        Optional<Specialist> specialistByEmailAndPassword = specialistRepository.getSpecialistByEmailAndPassword(email, password);
        if (specialistByEmailAndPassword.isPresent()) {
            return specialistMapper.toSpecialistDto(specialistByEmailAndPassword.get());
        }
        throw new NotFoundUserException("{user.not.login}");
    }

    @Override
    public void checkDuplicateEmail(String email) throws DuplicateEmailException {
        Optional<Specialist> specialistByEmail = specialistRepository.getSpecialistByEmail(email);
        if (specialistByEmail.isPresent()) {
            throw new DuplicateEmailException("{email.duplicated}");
        }
    }
}
