package ir.maktab.dtos;

/**
 * @author : Bahar Zolfaghari
 **/
public class AddressDto {
    private Integer id;
    private String city;
    private String state;
    private String formattedAddress;

    public Integer getId() {
        return id;
    }

    public AddressDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public AddressDto setState(String state) {
        this.state = state;
        return this;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public AddressDto setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
        return this;
    }
}
