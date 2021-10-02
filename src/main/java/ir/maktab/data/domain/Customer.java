package ir.maktab.data.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class Customer extends User {
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    public Set<Order> getOrders() {
        return orders;
    }

    public Customer setOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }
}
