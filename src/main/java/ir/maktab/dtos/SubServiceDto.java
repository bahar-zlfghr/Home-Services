package ir.maktab.dtos;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public class SubServiceDto {
    private Integer id;
    private String name;
    private Long basePrice;
    private String description;
    private ServiceDto serviceDto;
    private Set<SpecialistDto> specialistDtos = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public SubServiceDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubServiceDto setName(String name) {
        this.name = name;
        return this;
    }

    public Long getBasePrice() {
        return basePrice;
    }

    public SubServiceDto setBasePrice(Long basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SubServiceDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public ServiceDto getServiceDto() {
        return serviceDto;
    }

    public SubServiceDto setServiceDto(ServiceDto serviceDto) {
        this.serviceDto = serviceDto;
        return this;
    }

    public Set<SpecialistDto> getSpecialistDtos() {
        return specialistDtos;
    }

    public SubServiceDto setSpecialistDtos(Set<SpecialistDto> specialistDtos) {
        this.specialistDtos = specialistDtos;
        return this;
    }
}
