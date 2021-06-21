package ir.maktab.service.user;

import ir.maktab.data.repository.customer.CustomerRepository;
import ir.maktab.data.repository.specialist.SpecialistRepository;
import ir.maktab.data.repository.user.UserSpecification;
import ir.maktab.dtos.UserDto;
import ir.maktab.dtos.filter.UserFilterDto;
import ir.maktab.mappers.user.UserMapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
public class UserServiceImpl implements UserService {
    private final CustomerRepository customerRepository;
    private final SpecialistRepository specialistRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(CustomerRepository customerRepository, SpecialistRepository specialistRepository, UserMapper userMapper) {
        this.customerRepository = customerRepository;
        this.specialistRepository = specialistRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Set<UserDto> filterUsers(UserFilterDto userFilterDto) {
        Set<UserDto> userDtos = new HashSet<>();
        userDtos.addAll(
                customerRepository.findAll(UserSpecification.filterCustomers(userFilterDto))
                        .stream().map(userMapper::toUserDto).collect(Collectors.toList()));
        userDtos.addAll(
                specialistRepository.findAll(UserSpecification.filterSpecialists(userFilterDto))
                        .stream().map(userMapper::toUserDto).collect(Collectors.toList()));
        return userDtos;
    }
}
