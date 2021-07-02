package ir.maktab.service.order;

import ir.maktab.data.domain.Address;
import ir.maktab.data.domain.Specialist;
import ir.maktab.data.enums.OrderStatus;
import ir.maktab.dtos.CustomerDto;
import ir.maktab.dtos.OrderDto;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.exceptions.InvalidSuggestedPriceException;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface OrderService {

    void saveOrder(OrderDto orderDto) throws InvalidSuggestedPriceException;
    void updateOrderSuggestedPrice(Integer id, Long suggestedPrice);
    void updateOrderDescription(Integer id, String description);
    void updateOrderAddress(Integer id, Address address);
    void updateOrderStatus(Integer id, OrderStatus orderStatus);
    void updateOrderSpecialist(Integer id, Specialist specialist);
    void deleteOrder(OrderDto orderDto);
    Set<OrderDto> getOrdersByCustomer(CustomerDto customerDto);
    Set<OrderDto> getOrdersBySpecialist(SpecialistDto specialistDto);
}
