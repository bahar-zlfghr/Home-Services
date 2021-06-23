package ir.maktab.data.domain;

import ir.maktab.data.enums.OfferStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date submitDate;

    private Long suggestedPrice;

    private Integer durationDoWork;

    @Temporal(TemporalType.TIME)
    private Date startTime;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "OFFER_ORDER_FK"))
    private Order order;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "specialist_id", foreignKey = @ForeignKey(name = "OFFER_SPECIALIST_FK"))
    private Specialist specialist;

    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    public Integer getId() {
        return id;
    }

    public Offer setId(Integer id) {
        this.id = id;
        return this;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public Offer setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
        return this;
    }

    public Long getSuggestedPrice() {
        return suggestedPrice;
    }

    public Offer setSuggestedPrice(Long suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
        return this;
    }

    public Integer getDurationDoWork() {
        return durationDoWork;
    }

    public Offer setDurationDoWork(Integer durationDoWork) {
        this.durationDoWork = durationDoWork;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Offer setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Offer setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public Offer setSpecialist(Specialist specialist) {
        this.specialist = specialist;
        return this;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public Offer setStatus(OfferStatus status) {
        this.status = status;
        return this;
    }
}
