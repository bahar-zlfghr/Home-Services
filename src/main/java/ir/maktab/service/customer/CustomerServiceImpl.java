package ir.maktab.service.customer;

import ir.maktab.data.domain.Customer;
import ir.maktab.data.enums.UserStatus;
import ir.maktab.data.repository.customer.CustomerRepository;
import ir.maktab.dtos.CustomerDto;
import ir.maktab.dtos.OrderDto;
import ir.maktab.exceptions.DuplicateEmailException;
import ir.maktab.exceptions.NotFoundUserException;
import ir.maktab.mappers.customer.CustomerMapper;
import ir.maktab.mappers.order.OrderMapper;
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
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final OrderMapper orderMapper;
    private final PasswordEncoder passwordEncoder;
    private final Environment environment;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, OrderMapper orderMapper, PasswordEncoder passwordEncoder, Environment environment) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.orderMapper = orderMapper;
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
    }

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        String password = customerDto.getPassword();
        customerDto.setPassword(passwordEncoder.encode(password));
        customerRepository.save(customerMapper.toCustomer(customerDto));
    }

    @Override
    public void updateCustomerStatus(Integer id, UserStatus userStatus) {
        customerRepository.updateCustomerStatus(id, userStatus);
    }

    @Override
    public void updateCustomerOrders(Integer id, Set<OrderDto> orderDtos) {
        customerRepository.updateCustomerOrders(id, orderDtos.stream().map(orderMapper::toOrder).collect(Collectors.toSet()));
    }

    @Override
    public void updateCustomerPassword(String email, String previousPassword, String newPassword) {
        customerRepository.updateCustomerPassword(email, previousPassword, newPassword);
    }

    @Override
    public void deleteCustomer(CustomerDto customerDto) {
        customerRepository.delete(customerMapper.toCustomer(customerDto));
    }

    @Override
    public CustomerDto getCustomerByEmail(String email) throws NotFoundUserException {
        Optional<Customer> customerByEmail = customerRepository.getCustomerByEmail(email);
        if (customerByEmail.isPresent()) {
            return customerMapper.toCustomerDto(customerByEmail.get());
        }
        throw new NotFoundUserException(environment.getRequiredProperty("customer.not.found"));
    }

    @Override
    public CustomerDto getCustomerByEmailAndPassword(String email, String password) throws NotFoundUserException {
        Optional<Customer> customerByEmail = customerRepository.getCustomerByEmail(email);
        if (customerByEmail.isPresent()) {
            Customer customer = customerByEmail.get();
            if (passwordEncoder.matches(password, customer.getPassword())) {
                return customerMapper.toCustomerDto(customer);
            }
        }
        throw new NotFoundUserException(environment.getRequiredProperty("user.not.login"));
    }

    @Override
    public void checkDuplicateEmail(String email) throws DuplicateEmailException {
        Optional<Customer> customerByEmail = customerRepository.getCustomerByEmail(email);
        if (customerByEmail.isPresent()) {
            throw new DuplicateEmailException(environment.getProperty("email.duplicated"));
        }
    }
}
