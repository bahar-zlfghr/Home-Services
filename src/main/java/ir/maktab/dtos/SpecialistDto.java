package ir.maktab.dtos;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public class SpecialistDto extends UserDto {
    private Integer score;
    private byte[] profilePicture;
    private Set<SubServiceDto> subServiceDtos = new HashSet<>();
    private Set<ServiceDto> serviceDtos = new HashSet<>();
    private Set<OfferDto> offerDtos = new HashSet<>();
    private Set<OrderDto> orderDtos = new HashSet<>();

    public Integer getScore() {
        return score;
    }

    public SpecialistDto setScore(Integer score) {
        this.score = score;
        return this;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public SpecialistDto setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public Set<SubServiceDto> getSubServiceDtos() {
        return subServiceDtos;
    }

    public SpecialistDto setSubServiceDtos(Set<SubServiceDto> subServiceDtos) {
        this.subServiceDtos = subServiceDtos;
        return this;
    }

    public Set<ServiceDto> getServiceDtos() {
        return serviceDtos;
    }

    public SpecialistDto setServiceDtos(Set<ServiceDto> serviceDtos) {
        this.serviceDtos = serviceDtos;
        return this;
    }

    public Set<OfferDto> getOfferDtos() {
        return offerDtos;
    }

    public SpecialistDto setOfferDtos(Set<OfferDto> offerDtos) {
        this.offerDtos = offerDtos;
        return this;
    }

    public Set<OrderDto> getOrderDtos() {
        return orderDtos;
    }

    public SpecialistDto setOrderDtos(Set<OrderDto> orderDtos) {
        this.orderDtos = orderDtos;
        return this;
    }
}
