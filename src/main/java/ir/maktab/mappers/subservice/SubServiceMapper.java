package ir.maktab.mappers.subservice;

import ir.maktab.data.domain.SubService;
import ir.maktab.dtos.SubServiceDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface SubServiceMapper {

    SubService toSubService(SubServiceDto subServiceDto);
    SubServiceDto toSubServiceDto(SubService subService);
}
