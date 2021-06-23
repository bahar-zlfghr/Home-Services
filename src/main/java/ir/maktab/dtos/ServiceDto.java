package ir.maktab.dtos;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public class ServiceDto {
    private Integer id;

    @Size(min = 4, max = 30, message = "{service.name.range}")
    private String name;

    private Set<SubServiceDto> subServiceDtos = new HashSet<>();
    private Set<SpecialistDto> specialistDtos = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public ServiceDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ServiceDto setName(String name) {
        this.name = name;
        return this;
    }

    public Set<SubServiceDto> getSubServiceDtos() {
        return subServiceDtos;
    }

    public ServiceDto setSubServiceDtos(Set<SubServiceDto> subServiceDtos) {
        this.subServiceDtos = subServiceDtos;
        return this;
    }

    public Set<SpecialistDto> getSpecialistDtos() {
        return specialistDtos;
    }

    public ServiceDto setSpecialistDtos(Set<SpecialistDto> specialistDtos) {
        this.specialistDtos = specialistDtos;
        return this;
    }
}
