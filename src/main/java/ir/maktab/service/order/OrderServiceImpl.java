package ir.maktab.service.order;

import ir.maktab.data.domain.Address;
import ir.maktab.data.domain.Specialist;
import ir.maktab.data.enums.OrderStatus;
import ir.maktab.data.repository.order.OrderRepository;
import ir.maktab.dtos.CustomerDto;
import ir.maktab.dtos.OrderDto;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.mappers.customer.CustomerMapper;
import ir.maktab.mappers.order.OrderMapper;
import ir.maktab.mappers.specialist.SpecialistMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerMapper customerMapper;
    private final SpecialistMapper specialistMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, CustomerMapper customerMapper, SpecialistMapper specialistMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerMapper = customerMapper;
        this.specialistMapper = specialistMapper;
    }

    @Override
    public void saveOrder(OrderDto orderDto) {
        orderDto.setStatus(OrderStatus.WAITING_FOR_SPECIALIST_SUGGESTION);
        orderRepository.save(orderMapper.toOrder(orderDto));
    }

    @Override
    public void updateOrderSuggestedPrice(Integer id, Long suggestedPrice) {
        orderRepository.updateOrderSuggestedPrice(id, suggestedPrice);
    }

    @Override
    public void updateOrderDescription(Integer id, String description) {
        orderRepository.updateOrderDescription(id, description);
    }

    @Override
    public void updateOrderAddress(Integer id, Address address) {
        orderRepository.updateOrderAddress(id, address);
    }

    @Override
    public void updateOrderStatus(Integer id, OrderStatus orderStatus) {
        orderRepository.updateOrderStatus(id, orderStatus);
    }

    @Override
    public void updateOrderSpecialist(Integer id, Specialist specialist) {
        orderRepository.updateOrderSpecialist(id, specialist);
    }

    @Override
    public void deleteOrder(OrderDto orderDto) {
        orderRepository.delete(orderMapper.toOrder(orderDto));
    }

    @Override
    public Set<OrderDto> getOrdersByCustomer(CustomerDto customerDto) {
        return orderRepository.getOrdersByCustomer(customerMapper.toCustomer(customerDto))
                .stream().map(orderMapper::toOrderDto).collect(Collectors.toSet());
    }

    @Override
    public Set<OrderDto> getOrdersBySpecialist(SpecialistDto specialistDto) {
        return orderRepository.getOrdersBySpecialist(specialistMapper.toSpecialist(specialistDto))
                .stream().map(orderMapper::toOrderDto).collect(Collectors.toSet());
    }
}
