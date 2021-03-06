package ir.maktab.service.specialist;

import ir.maktab.data.domain.Specialist;
import ir.maktab.data.enums.UserStatus;
import ir.maktab.data.repository.specialist.SpecialistRepository;
import ir.maktab.dtos.*;
import ir.maktab.exceptions.DuplicateEmailException;
import ir.maktab.exceptions.NotEmptyException;
import ir.maktab.exceptions.NotFoundUserException;
import ir.maktab.mappers.offer.OfferMapper;
import ir.maktab.mappers.order.OrderMapper;
import ir.maktab.mappers.service.ServiceMapper;
import ir.maktab.mappers.specialist.SpecialistMapper;
import ir.maktab.mappers.subservice.SubServiceMapper;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class SpecialistServiceImpl implements SpecialistService {
    private final SpecialistRepository specialistRepository;
    private final SpecialistMapper specialistMapper;
    private final SubServiceMapper subServiceMapper;
    private final ServiceMapper serviceMapper;
    private final OfferMapper offerMapper;
    private final OrderMapper orderMapper;
    private final PasswordEncoder passwordEncoder;
    private final Environment environment;

    public SpecialistServiceImpl(SpecialistRepository specialistRepository, SpecialistMapper specialistMapper, SubServiceMapper subServiceMapper, ServiceMapper serviceMapper, OfferMapper offerMapper, OrderMapper orderMapper, PasswordEncoder passwordEncoder, Environment environment) {
        this.specialistRepository = specialistRepository;
        this.specialistMapper = specialistMapper;
        this.subServiceMapper = subServiceMapper;
        this.serviceMapper = serviceMapper;
        this.offerMapper = offerMapper;
        this.orderMapper = orderMapper;
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
    }

    @Override
    public void saveSpecialist(SpecialistDto specialistDto) throws NotEmptyException, DuplicateEmailException {
        checkDuplicateEmail(specialistDto.getEmail());
        if (specialistDto.getProfilePicture().length == 0) {
            throw new NotEmptyException(environment.getProperty("specialist.profile.picture.not.empty"));
        }
        String password = specialistDto.getPassword();
        specialistDto.setPassword(passwordEncoder.encode(password));
        specialistRepository.save(specialistMapper.toSpecialist(specialistDto));
    }

    @Override
    public void updateSpecialistStatus(Integer id, UserStatus status) {
        specialistRepository.updateSpecialistStatus(id, status);
    }

    @Override
    public void updateSpecialistScore(Integer id, Integer score) {
        specialistRepository.updateSpecialistScore(id, score);
    }

    @Override
    public void updateSpecialistProfilePicture(Integer id, byte[] profilePicture) {
        specialistRepository.updateSpecialistProfilePicture(id, profilePicture);
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
        throw new NotFoundUserException(environment.getProperty("specialist.not.found"));
    }

    @Override
    public SpecialistDto getSpecialistByEmailAndPassword(String email, String password) throws NotFoundUserException {
        Optional<Specialist> specialistByEmailAndPassword = specialistRepository.getSpecialistByEmailAndPassword(email, password);
        if (specialistByEmailAndPassword.isPresent()) {
            return specialistMapper.toSpecialistDto(specialistByEmailAndPassword.get());
        }
        throw new NotFoundUserException(environment.getProperty("user.not.login"));
    }

    @Override
    public void checkDuplicateEmail(String email) throws DuplicateEmailException {
        Optional<Specialist> specialistByEmail = specialistRepository.getSpecialistByEmail(email);
        if (specialistByEmail.isPresent()) {
            throw new DuplicateEmailException(environment.getProperty("user.email.duplicated"));
        }
    }

    @Override
    public Set<SpecialistDto> getAllSpecialists() {
        return specialistRepository.getAllSpecialists().stream().map(specialistMapper::toSpecialistDto).collect(Collectors.toSet());
    }
}
