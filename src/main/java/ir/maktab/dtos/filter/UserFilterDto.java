package ir.maktab.dtos.filter;

import ir.maktab.data.enums.PersonRole;

/**
 * @author : Bahar Zolfaghari
 **/
public class UserFilterDto {
    private PersonRole role;
    private String name;
    private String family;
    private String Email;
    private String speciality;
    private String score;

    public PersonRole getRole() {
        return role;
    }

    public UserFilterDto setRole(PersonRole role) {
        this.role = role;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserFilterDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public UserFilterDto setFamily(String family) {
        this.family = family;
        return this;
    }

    public String getEmail() {
        return Email;
    }

    public UserFilterDto setEmail(String email) {
        Email = email;
        return this;
    }

    public String getSpeciality() {
        return speciality;
    }

    public UserFilterDto setSpeciality(String speciality) {
        this.speciality = speciality;
        return this;
    }

    public String getScore() {
        return score;
    }

    public UserFilterDto setScore(String score) {
        this.score = score;
        return this;
    }
}
