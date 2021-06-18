package ir.maktab.mappers.address;

import ir.maktab.data.domain.Address;
import ir.maktab.dtos.AddressDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface AddressMapper {

    Address toAddress(AddressDto addressDto);
    AddressDto toAddressDto(Address address);
}
