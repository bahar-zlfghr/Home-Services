package ir.maktab.service.user;

import ir.maktab.data.repository.customer.CustomerRepository;
import ir.maktab.data.repository.specialist.SpecialistRepository;
import ir.maktab.data.repository.user.CustomerSpecification;
import ir.maktab.data.repository.user.SpecialistSpecification;
import ir.maktab.dtos.filter.UserFilterDto;
import ir.maktab.dtos.filter.UserFilterResult;
import ir.maktab.mappers.customer.CustomerMapper;
import ir.maktab.mappers.specialist.SpecialistMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final CustomerRepository customerRepository;
    private final SpecialistRepository specialistRepository;
    private final CustomerMapper customerMapper;
    private final SpecialistMapper specialistMapper;

    public UserServiceImpl(CustomerRepository customerRepository, SpecialistRepository specialistRepository, CustomerMapper customerMapper, SpecialistMapper specialistMapper) {
        this.customerRepository = customerRepository;
        this.specialistRepository = specialistRepository;
        this.customerMapper = customerMapper;
        this.specialistMapper = specialistMapper;
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
}
