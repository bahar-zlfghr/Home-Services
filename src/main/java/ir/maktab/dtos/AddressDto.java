package ir.maktab.dtos;

/**
 * @author : Bahar Zolfaghari
 **/
public class AddressDto {
    private Integer id;
    private String city;
    private String state;
    private String formatted_address;

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

    public String getFormatted_address() {
        return formatted_address;
    }

    public AddressDto setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
        return this;
    }
}
