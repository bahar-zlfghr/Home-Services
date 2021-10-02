package ir.maktab.dtos;

import ir.maktab.data.enums.OrderStatus;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public class OrderDto {
    private Integer id;
    private CustomerDto customerDto;
    private SubServiceDto subServiceDto;
    private Long suggestedPrice;
    private String description;
    private Date orderRegistrationDate;
    private Date doDate;
    private AddressDto addressDto;
    private OrderStatus status;
    private SpecialistDto specialistDto;
    private Set<OfferDto> offerDtos = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public OrderDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public OrderDto setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
        return this;
    }

    public SubServiceDto getSubServiceDto() {
        return subServiceDto;
    }

    public OrderDto setSubServiceDto(SubServiceDto subServiceDto) {
        this.subServiceDto = subServiceDto;
        return this;
    }

    public Long getSuggestedPrice() {
        return suggestedPrice;
    }

    public OrderDto setSuggestedPrice(Long suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getOrderRegistrationDate() {
        return orderRegistrationDate;
    }

    public OrderDto setOrderRegistrationDate(Date orderRegistrationDate) {
        this.orderRegistrationDate = orderRegistrationDate;
        return this;
    }

    public Date getDoDate() {
        return doDate;
    }

    public OrderDto setDoDate(Date doDate) {
        this.doDate = doDate;
        return this;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public OrderDto setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public OrderDto setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public SpecialistDto getSpecialistDto() {
        return specialistDto;
    }

    public OrderDto setSpecialistDto(SpecialistDto specialistDto) {
        this.specialistDto = specialistDto;
        return this;
    }

    public Set<OfferDto> getOfferDtos() {
        return offerDtos;
    }

    public OrderDto setOfferDtos(Set<OfferDto> offerDtos) {
        this.offerDtos = offerDtos;
        return this;
    }
}
