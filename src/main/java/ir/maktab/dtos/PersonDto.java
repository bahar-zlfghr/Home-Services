package ir.maktab.dtos;

import ir.maktab.validationgroup.RegistrationGroup;
import ir.maktab.validators.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author : Bahar Zolfaghari
 **/
public class PersonDto {
    private Integer id;

    @Size(min = 3, max = 30, message = "{user.name.range}", groups = {RegistrationGroup.class})
    private String name;

    @Size(min = 3, max = 30, message = "{user.family.range}", groups = {RegistrationGroup.class})
    private String family;

    @NotBlank(message = "{user.email.valid}", groups = {RegistrationGroup.class})
    @Email(message = "{user.email.valid}", groups = {RegistrationGroup.class})
    private String email;

    @Password(groups = {RegistrationGroup.class})
    private String password;

    @NotBlank(message = "{user.role.required}", groups = {RegistrationGroup.class})
    private String role;

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

    public String getRole() {
        return role;
    }

    public PersonDto setRole(String role) {
        this.role = role;
        return this;
    }
}
