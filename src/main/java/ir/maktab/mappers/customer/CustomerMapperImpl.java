package ir.maktab.mappers.customer;

import ir.maktab.data.domain.Customer;
import ir.maktab.dtos.CustomerDto;
import ir.maktab.mappers.order.OrderMapper;
import ir.maktab.mappers.user.UserMapper;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
public class CustomerMapperImpl implements CustomerMapper {
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;

    @Lazy
    public CustomerMapperImpl(OrderMapper orderMapper, UserMapper userMapper) {
        this.orderMapper = orderMapper;
        this.userMapper = userMapper;
    }

    @Override
    public Customer toCustomer(CustomerDto customerDto) {
        Customer customer = (Customer) userMapper.toUser(customerDto);
        return customer
                .setOrders(customerDto.getOrderDtos().stream().map(orderMapper::toOrder).collect(Collectors.toSet()));
    }

    @Override
    public CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = (CustomerDto) userMapper.toUserDto(customer);
        return customerDto
                .setOrderDtos(customer.getOrders().stream().map(orderMapper::toOrderDto).collect(Collectors.toSet()));
    }
}
