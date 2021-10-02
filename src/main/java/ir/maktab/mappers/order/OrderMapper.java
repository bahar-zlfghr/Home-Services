package ir.maktab.mappers.order;

import ir.maktab.data.domain.Order;
import ir.maktab.dtos.OrderDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface OrderMapper {

    Order toOrder(OrderDto orderDto);
    OrderDto toOrderDto(Order order);
}
