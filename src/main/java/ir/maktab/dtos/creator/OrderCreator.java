package ir.maktab.dtos.creator;

import ir.maktab.dtos.AddressDto;
import ir.maktab.dtos.CustomerDto;
import ir.maktab.dtos.OrderDto;
import ir.maktab.dtos.SubServiceDto;

import java.util.Date;

/**
 * @author : Bahar Zolfaghari
 **/
public interface OrderCreator {

    static OrderDto createOrder(CustomerDto customerDto, SubServiceDto subServiceDto, String suggestedPrice,
                                String description, Date doDate, AddressDto addressDto) {
        return new OrderDto()
                .setCustomerDto(customerDto)
                .setSubServiceDto(subServiceDto)
                .setSuggestedPrice(Long.valueOf(suggestedPrice))
                .setDescription(description)
                .setDoDate(doDate)
                .setAddressDto(addressDto);
    }
}
