package ir.maktab.data.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class Specialist extends User {
    private Integer score;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] profilePicture;

    @ManyToMany(mappedBy = "specialists", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<SubService> subServices = new HashSet<>();

    @ManyToMany(mappedBy = "specialists", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Service> services = new HashSet<>();

    @OneToMany(mappedBy = "specialist", cascade = {CascadeType.ALL, CascadeType.PERSIST}, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Offer> offers = new HashSet<>();

    @OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    public Integer getScore() {
        return score;
    }

    public Specialist setScore(Integer score) {
        this.score = score;
        return this;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public Specialist setProfilePicture(byte[] profilePicture) {
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
