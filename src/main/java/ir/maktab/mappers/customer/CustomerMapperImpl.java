package ir.maktab.mappers.customer;

import ir.maktab.data.domain.Customer;
import ir.maktab.dtos.CustomerDto;
import ir.maktab.mappers.account.AccountMapper;
import ir.maktab.mappers.order.OrderMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class CustomerMapperImpl implements CustomerMapper {
    private final OrderMapper orderMapper;
    private final AccountMapper accountMapper;

    @Lazy
    public CustomerMapperImpl(OrderMapper orderMapper, AccountMapper accountMapper) {
        this.orderMapper = orderMapper;
        this.accountMapper = accountMapper;
    }

    @Override
    public Customer toCustomer(CustomerDto customerDto) {
        if (customerDto != null) {
            return (Customer) new Customer()
                    .setStatus(customerDto.getStatus())
                    .setAccount(accountMapper.toAccount(customerDto.getAccountDto()))
                    .setId(customerDto.getId())
                    .setName(customerDto.getName())
                    .setFamily(customerDto.getFamily())
                    .setEmail(customerDto.getEmail())
                    .setPassword(customerDto.getPassword())
                    .setRole(customerDto.getRole());
        }
        return null;
    }

    @Override
    public CustomerDto toCustomerDto(Customer customer) {
        if (customer != null) {
            return (CustomerDto) new CustomerDto()
                    .setStatus(customer.getStatus())
                    .setAccountDto(accountMapper.toAccountDto(customer.getAccount()))
                    .setId(customer.getId())
                    .setName(customer.getName())
                    .setFamily(customer.getFamily())
                    .setEmail(customer.getEmail())
                    .setPassword(customer.getPassword())
                    .setRole(customer.getRole());
        }
        return null;
    }
}
