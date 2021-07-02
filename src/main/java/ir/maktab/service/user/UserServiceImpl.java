package ir.maktab.service.user;

import ir.maktab.data.domain.User;
import ir.maktab.data.enums.UserStatus;
import ir.maktab.data.repository.customer.CustomerRepository;
import ir.maktab.data.repository.specialist.SpecialistRepository;
import ir.maktab.data.repository.customer.CustomerSpecification;
import ir.maktab.data.repository.specialist.SpecialistSpecification;
import ir.maktab.data.repository.user.UserRepository;
import ir.maktab.dtos.ConfirmationTokenDto;
import ir.maktab.dtos.UserDto;
import ir.maktab.dtos.filter.UserFilterDto;
import ir.maktab.dtos.filter.UserFilterResult;
import ir.maktab.exceptions.NotFoundUserException;
import ir.maktab.mappers.customer.CustomerMapper;
import ir.maktab.mappers.specialist.SpecialistMapper;
import ir.maktab.mappers.token.ConfirmationTokenMapper;
import ir.maktab.mappers.user.UserMapper;
import ir.maktab.service.customer.CustomerService;
import ir.maktab.service.specialist.SpecialistService;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final SpecialistRepository specialistRepository;
    private final CustomerMapper customerMapper;
    private final SpecialistMapper specialistMapper;
    private final UserMapper userMapper;
    private final ConfirmationTokenMapper confirmationTokenMapper;
    private final CustomerService customerService;
    private final SpecialistService specialistService;
    private final Environment environment;

    public UserServiceImpl(UserRepository userRepository, CustomerRepository customerRepository, SpecialistRepository specialistRepository,
                           CustomerMapper customerMapper, SpecialistMapper specialistMapper, UserMapper userMapper, ConfirmationTokenMapper confirmationTokenMapper, CustomerService customerService,
                           SpecialistService specialistService, Environment environment) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.specialistRepository = specialistRepository;
        this.customerMapper = customerMapper;
        this.specialistMapper = specialistMapper;
        this.userMapper = userMapper;
        this.confirmationTokenMapper = confirmationTokenMapper;
        this.customerService = customerService;
        this.specialistService = specialistService;
        this.environment = environment;
    }

    @Override
    public UserFilterResult filterUsers(UserFilterDto userFilterDto) {
        UserFilterResult result = new UserFilterResult();
        result.getCustomerDtos().addAll(customerRepository.findAll(Specification.where(CustomerSpecification.filterCustomers(userFilterDto)))
                .stream().map(customerMapper::toCustomerDto).collect(Collectors.toSet()));
        result.getSpecialistDtos().addAll(specialistRepository.findAll(Specification.where(SpecialistSpecification.filterSpecialists(userFilterDto)))
                .stream().map(specialistMapper::toSpecialistDto).collect(Collectors.toSet()));
        return result;
    }

    @Override
    public UserDto findUserByConfirmToken(ConfirmationTokenDto confirmationTokenDto) throws NotFoundUserException {
        Optional<User> byEmail = userRepository.findByConfirmationToken(confirmationTokenMapper.toConfirmationToken(confirmationTokenDto));
        if (byEmail.isPresent()) {
            return userMapper.toUserDto(byEmail.get());
        }
        throw new  NotFoundUserException(environment.getProperty("user.not.found"));
    }

    @Override
    public void updateUserStatus(UserDto userDto) {
        switch (userDto.getRole()) {
            case CUSTOMER:
                userDto.setStatus(UserStatus.APPROVED);
                customerService.updateCustomerStatus(userDto.getId(), userDto.getStatus());
                break;
            case SPECIALIST:
                userDto.setStatus(UserStatus.AWAITING_APPROVAL);
                specialistService.updateSpecialistStatus(userDto.getId(), userDto.getStatus());
                break;
        }
    }
}
