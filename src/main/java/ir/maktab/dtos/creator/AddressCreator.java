package ir.maktab.dtos.creator;

import ir.maktab.dtos.AddressDto;

/**
 * @author : Bahar Zolfaghari
 **/
public interface AddressCreator {

    static AddressDto createAddress(String state, String city, String formattedAddress) {
        return new AddressDto()
                .setState(state)
                .setCity(city)
                .setFormattedAddress(formattedAddress);
    }
}
