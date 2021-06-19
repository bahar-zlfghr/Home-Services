package ir.maktab.service.address;

import ir.maktab.data.repository.address.AddressRepository;
import ir.maktab.dtos.AddressDto;
import ir.maktab.mappers.address.AddressMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : Bahar Zolfaghari
 **/
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public void saveAddress(AddressDto addressDto) {
        addressRepository.save(addressMapper.toAddress(addressDto));
    }

    @Override
    public void updateCityAddress(Integer id, String city) {
        addressRepository.updateCityAddress(id, city);
    }

    @Override
    public void updateStateAddress(Integer id, String state) {
        addressRepository.updateStateAddress(id, state);
    }

    @Override
    public void updateAlleyAddress(Integer id, String formatted_address) {
        addressRepository.updateAlleyAddress(id, formatted_address);
    }

    @Override
    public void deleteAddress(AddressDto addressDto) {
        addressRepository.delete(addressMapper.toAddress(addressDto));
    }

    @Override
    public Set<AddressDto> getAddressesByCity(String city) {
        return addressRepository.getAddressesByCity(city).stream().map(addressMapper::toAddressDto).collect(Collectors.toSet());
    }

    @Override
    public Set<AddressDto> getAddressesByState(String state) {
        return addressRepository.getAddressesByState(state).stream().map(addressMapper::toAddressDto).collect(Collectors.toSet());
    }
}
