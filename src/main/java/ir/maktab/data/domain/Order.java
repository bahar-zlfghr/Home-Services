package ir.maktab.data.domain;

import ir.maktab.data.enums.OrderStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "ORDER_CUSTOMER_FK"))
    private Customer customer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subService_id", foreignKey = @ForeignKey(name = "ORDER_SUB_SERVICE_FK"))
    private SubService subService;

    private Long suggestedPrice;

    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderRegistrationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date doDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "ORDER_ADDRESS_FK"))
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "specialist_id", foreignKey = @ForeignKey(name = "ORDER_SPECIALIST_FK"))
    private Specialist specialist;

    @OneToMany(mappedBy = "specialist", cascade = {CascadeType.ALL, CascadeType.PERSIST})
    private Set<Offer> offers = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public Order setId(Integer id) {
        this.id = id;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Order setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public SubService getSubService() {
        return subService;
    }

    public Order setSubService(SubService subService) {
        this.subService = subService;
        return this;
    }

    public Long getSuggestedPrice() {
        return suggestedPrice;
    }

    public Order setSuggestedPrice(Long suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Order setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getOrderRegistrationDate() {
        return orderRegistrationDate;
    }

    public Order setOrderRegistrationDate(Date orderRegistrationDate) {
        this.orderRegistrationDate = orderRegistrationDate;
        return this;
    }

    public Date getDoDate() {
        return doDate;
    }

    public Order setDoDate(Date doDate) {
        this.doDate = doDate;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Order setAddress(Address address) {
        this.address = address;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public Order setSpecialist(Specialist specialist) {
        this.specialist = specialist;
        return this;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public Order setOffers(Set<Offer> offers) {
        this.offers = offers;
        return this;
    }
}
