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
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final OrderMapper orderMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, OrderMapper orderMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public void saveCustomer(CustomerDto customerDto) {
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
        throw new NotFoundUserException("customer.not.found");
    }

    @Override
    public CustomerDto getCustomerByEmailAndPassword(String email, String password) throws NotFoundUserException {
        Optional<Customer> customerByEmailAndPassword = customerRepository.getCustomerByEmailAndPassword(email, password);
        if (customerByEmailAndPassword.isPresent()) {
            return customerMapper.toCustomerDto(customerByEmailAndPassword.get());
        }
        throw new NotFoundUserException("user.not.login");
    }

    @Override
    public void checkDuplicateEmail(String email) throws DuplicateEmailException {
        Optional<Customer> customerByEmail = customerRepository.getCustomerByEmail(email);
        if (customerByEmail.isPresent()) {
            throw new DuplicateEmailException("email.duplicated");
        }
    }
}
