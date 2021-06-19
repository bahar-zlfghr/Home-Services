package ir.maktab.service.address;

import ir.maktab.dtos.AddressDto;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface AddressService {

    void saveAddress(AddressDto addressDto);
    void updateCityAddress(Integer id, String city);
    void updateStateAddress(Integer id, String state);
    void updateAlleyAddress(Integer id, String formatted_address);
    void deleteAddress(AddressDto addressDto);
    Set<AddressDto> getAddressesByCity(String city);
    Set<AddressDto> getAddressesByState(String state);
}
