package ir.maktab.dtos.factory;

import ir.maktab.dtos.ServiceDto;
import ir.maktab.dtos.SubServiceDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface SubserviceFactory {
    static SubServiceDto createSubService(ServiceDto serviceDto, String subServiceName, String basePrice, String description) {
        return new SubServiceDto()
                .setName(subServiceName)
                .setBasePrice(Long.valueOf(basePrice))
                .setDescription(description)
                .setServiceDto(serviceDto);
    }
}
