package ir.maktab.mappers.customer;

import ir.maktab.data.domain.Customer;
import ir.maktab.dtos.CustomerDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface CustomerMapper {

    Customer toCustomer(CustomerDto customerDto);
    CustomerDto toCustomerDto(Customer customer);
}
