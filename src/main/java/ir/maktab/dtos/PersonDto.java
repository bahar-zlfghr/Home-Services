package ir.maktab.dtos;

import ir.maktab.data.enums.PersonRole;

import javax.persistence.*;

/**
 * @author : Bahar Zolfaghari
 **/
public class PersonDto {
    private Integer id;
    private String name;
    private String family;
    private String email;
    private String password;
    private PersonRole role;

    public Integer getId() {
        return id;
    }

    public PersonDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PersonDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public PersonDto setFamily(String family) {
        this.family = family;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PersonDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public PersonDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public PersonRole getRole() {
        return role;
    }

    public PersonDto setRole(PersonRole role) {
        this.role = role;
        return this;
    }
}
