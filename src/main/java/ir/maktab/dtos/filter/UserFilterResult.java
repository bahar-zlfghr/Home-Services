package ir.maktab.dtos.filter;

import ir.maktab.dtos.CustomerDto;
import ir.maktab.dtos.SpecialistDto;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public class UserFilterResult {
    private Set<CustomerDto> customerDtos = new HashSet<>();
    private Set<SpecialistDto> specialistDtos = new HashSet<>();

    public Set<CustomerDto> getCustomerDtos() {
        return customerDtos;
    }

    public UserFilterResult setCustomerDtos(Set<CustomerDto> customerDtos) {
        this.customerDtos = customerDtos;
        return this;
    }

    public Set<SpecialistDto> getSpecialistDtos() {
        return specialistDtos;
    }

    public UserFilterResult setSpecialistDtos(Set<SpecialistDto> specialistDtos) {
        this.specialistDtos = specialistDtos;
        return this;
    }
}
