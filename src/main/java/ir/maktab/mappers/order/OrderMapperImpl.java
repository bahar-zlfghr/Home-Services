package ir.maktab.mappers.order;

import ir.maktab.data.domain.Order;
import ir.maktab.dtos.OrderDto;
import ir.maktab.mappers.address.AddressMapper;
import ir.maktab.mappers.customer.CustomerMapper;
import ir.maktab.mappers.offer.OfferMapper;
import ir.maktab.mappers.specialist.SpecialistMapper;
import ir.maktab.mappers.subservice.SubServiceMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class OrderMapperImpl implements OrderMapper {
    private final CustomerMapper customerMapper;
    private final SubServiceMapper subServiceMapper;
    private final AddressMapper addressMapper;
    private final SpecialistMapper specialistMapper;
    private final OfferMapper offerMapper;

    @Lazy
    public OrderMapperImpl(CustomerMapper customerMapper, SubServiceMapper subServiceMapper, AddressMapper addressMapper, SpecialistMapper specialistMapper, OfferMapper offerMapper) {
        this.customerMapper = customerMapper;
        this.subServiceMapper = subServiceMapper;
        this.addressMapper = addressMapper;
        this.specialistMapper = specialistMapper;
        this.offerMapper = offerMapper;
    }

    @Override
    public Order toOrder(OrderDto orderDto) {
        if (orderDto != null) {
            return new Order()
                    .setId(orderDto.getId())
                    .setCustomer(customerMapper.toCustomer(orderDto.getCustomerDto()))
                    .setSubService(subServiceMapper.toSubService(orderDto.getSubServiceDto()))
                    .setSuggestedPrice(orderDto.getSuggestedPrice())
                    .setDescription(orderDto.getDescription())
                    .setOrderRegistrationDate(orderDto.getOrderRegistrationDate())
                    .setDoDate(orderDto.getDoDate())
                    .setAddress(addressMapper.toAddress(orderDto.getAddressDto()))
                    .setStatus(orderDto.getStatus())
                    .setSpecialist(specialistMapper.toSpecialist(orderDto.getSpecialistDto()))
                    .setOffers(orderDto.getOfferDtos().stream().map(offerMapper::toOffer).collect(Collectors.toSet()));
        }
        return null;
    }

    @Override
    public OrderDto toOrderDto(Order order) {
        if (order != null) {
            return new OrderDto()
                    .setId(order.getId())
                    .setCustomerDto(customerMapper.toCustomerDto(order.getCustomer()))
                    .setSubServiceDto(subServiceMapper.toSubServiceDto(order.getSubService()))
                    .setSuggestedPrice(order.getSuggestedPrice())
                    .setDescription(order.getDescription())
                    .setOrderRegistrationDate(order.getOrderRegistrationDate())
                    .setDoDate(order.getDoDate())
                    .setAddressDto(addressMapper.toAddressDto(order.getAddress()))
                    .setStatus(order.getStatus())
                    .setSpecialistDto(specialistMapper.toSpecialistDto(order.getSpecialist()))
                    .setOfferDtos(order.getOffers().stream().map(offerMapper::toOfferDto).collect(Collectors.toSet()));
        }
        return null;
    }
}
