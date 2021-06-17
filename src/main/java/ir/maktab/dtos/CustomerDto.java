package ir.maktab.dtos;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public class CustomerDto extends UserDto {
    private Set<OrderDto> orderDtos = new HashSet<>();

    public Set<OrderDto> getOrderDtos() {
        return orderDtos;
    }

    public CustomerDto setOrderDtos(Set<OrderDto> orderDtos) {
        this.orderDtos = orderDtos;
        return this;
    }
}
