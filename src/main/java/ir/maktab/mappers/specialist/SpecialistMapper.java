package ir.maktab.mappers.specialist;

import ir.maktab.data.domain.Specialist;
import ir.maktab.dtos.SpecialistDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface SpecialistMapper {

    Specialist toSpecialist(SpecialistDto specialistDto);
    SpecialistDto toSpecialistDto(Specialist specialist);
}
