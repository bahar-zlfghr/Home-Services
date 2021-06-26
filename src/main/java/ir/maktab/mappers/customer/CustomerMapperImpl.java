package ir.maktab.mappers.customer;

import ir.maktab.data.domain.Customer;
import ir.maktab.dtos.CustomerDto;
import ir.maktab.mappers.account.AccountMapper;
import ir.maktab.mappers.order.OrderMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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
        return (Customer) new Customer()
                .setOrders(customerDto.getOrderDtos().stream().map(orderMapper::toOrder).collect(Collectors.toSet()))
                .setStatus(customerDto.getStatus())
                .setAccount(accountMapper.toAccount(customerDto.getAccountDto()))
                .setId(customerDto.getId())
                .setName(customerDto.getName())
                .setFamily(customerDto.getFamily())
                .setEmail(customerDto.getEmail())
                .setPassword(customerDto.getPassword())
                .setRole(customerDto.getRole());
    }

    @Override
    public CustomerDto toCustomerDto(Customer customer) {
        return (CustomerDto) new CustomerDto()
                .setOrderDtos(customer.getOrders().stream().map(orderMapper::toOrderDto).collect(Collectors.toSet()))
                .setStatus(customer.getStatus())
                .setAccountDto(accountMapper.toAccountDto(customer.getAccount()))
                .setId(customer.getId())
                .setName(customer.getName())
                .setFamily(customer.getFamily())
                .setEmail(customer.getEmail())
                .setPassword(customer.getPassword())
                .setRole(customer.getRole());
    }
}
