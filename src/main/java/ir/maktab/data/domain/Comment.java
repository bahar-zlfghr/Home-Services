package ir.maktab.data.domain;

import javax.persistence.*;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer score;
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "COMMENT_CUSTOMER_FK"))
    private Customer customer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "specialist_id", foreignKey = @ForeignKey(name = "COMMENT_SPECIALIST_FK"))
    private Specialist specialist;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "COMMENT_ORDER_FK"))
    private Order order;

    public Integer getId() {
        return id;
    }

    public Comment setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public Comment setScore(Integer score) {
        this.score = score;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Comment setDescription(String description) {
        this.description = description;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Comment setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public Comment setSpecialist(Specialist specialist) {
        this.specialist = specialist;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Comment setOrder(Order order) {
        this.order = order;
        return this;
    }
}
