package ir.maktab.mappers.address;

import ir.maktab.data.domain.Address;
import ir.maktab.dtos.AddressDto;
import org.springframework.stereotype.Component;

/**
 * @author : Bahar Zolfaghari
 **/
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toAddress(AddressDto addressDto) {
        if (addressDto != null) {
            return new Address()
                    .setId(addressDto.getId())
                    .setCity(addressDto.getCity())
                    .setState(addressDto.getState())
                    .setFormattedAddress(addressDto.getFormattedAddress());
        }
        return null;
    }

    @Override
    public AddressDto toAddressDto(Address address) {
        if (address != null) {
            return new AddressDto()
                    .setId(address.getId())
                    .setCity(address.getCity())
                    .setState(address.getState())
                    .setFormattedAddress(address.getFormattedAddress());
        }
        return null;
    }
}
