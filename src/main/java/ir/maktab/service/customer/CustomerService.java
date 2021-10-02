package ir.maktab.service.customer;

import ir.maktab.data.enums.UserStatus;
import ir.maktab.dtos.CustomerDto;
import ir.maktab.dtos.OrderDto;
import ir.maktab.exceptions.DuplicateEmailException;
import ir.maktab.exceptions.NotFoundUserException;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface CustomerService {

    void saveCustomer(CustomerDto customerDto) throws DuplicateEmailException;
    void updateCustomerStatus(Integer id, UserStatus status);
    void updateCustomerOrders(Integer id, Set<OrderDto> orderDtos);
    void updateCustomerPassword(String email, String previousPassword, String newPassword);
    void deleteCustomer(CustomerDto customerDto);
    CustomerDto getCustomerByEmail(String email) throws NotFoundUserException;
    CustomerDto getCustomerByEmailAndPassword(String email, String password) throws NotFoundUserException;
    void checkDuplicateEmail(String email) throws DuplicateEmailException;
}
