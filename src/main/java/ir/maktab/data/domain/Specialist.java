package ir.maktab.data.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class Specialist extends User {
    @Column(nullable = false)
    private String specialty;

    private Integer score;

    @OneToOne(mappedBy = "specialist", cascade = {CascadeType.ALL, CascadeType.PERSIST})
    private ProfilePicture profilePicture;

    @ManyToMany(mappedBy = "specialists", cascade = CascadeType.PERSIST)
    private Set<SubService> subServices = new HashSet<>();

    @ManyToMany(mappedBy = "specialists", cascade = CascadeType.PERSIST)
    private Set<Service> services = new HashSet<>();

    @OneToMany(mappedBy = "specialist", cascade = {CascadeType.ALL, CascadeType.PERSIST}, orphanRemoval = true)
    private Set<Offer> offers = new HashSet<>();

    @OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    public String getSpecialty() {
        return specialty;
    }

    public Specialist setSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public Specialist setScore(Integer score) {
        this.score = score;
        return this;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public Specialist setProfilePicture(ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public Set<SubService> getSubServices() {
        return subServices;
    }

    public Specialist setSubServices(Set<SubService> subServices) {
        this.subServices = subServices;
        return this;
    }

    public Set<Service> getServices() {
        return services;
    }

    public Specialist setServices(Set<Service> services) {
        this.services = services;
        return this;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public Specialist setOffers(Set<Offer> offers) {
        this.offers = offers;
        return this;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Specialist setOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }
}
