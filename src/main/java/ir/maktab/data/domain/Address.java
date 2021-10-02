package ir.maktab.data.domain;

import javax.persistence.*;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String city;
    private String state;

    @Column(name = "address")
    private String formattedAddress;

    public Integer getId() {
        return id;
    }

    public Address setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public Address setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
        return this;
    }
}
