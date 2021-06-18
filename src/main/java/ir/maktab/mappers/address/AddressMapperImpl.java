package ir.maktab.mappers.address;

import ir.maktab.data.domain.Address;
import ir.maktab.dtos.AddressDto;

/**
 * @author : Bahar Zolfaghari
 **/
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toAddress(AddressDto addressDto) {
        return new Address()
                .setId(addressDto.getId())
                .setCity(addressDto.getCity())
                .setState(addressDto.getState())
                .setFormatted_address(addressDto.getFormatted_address());
    }

    @Override
    public AddressDto toAddressDto(Address address) {
        return new AddressDto()
                .setId(address.getId())
                .setCity(address.getCity())
                .setState(address.getState())
                .setFormatted_address(address.getFormatted_address());
    }
}
