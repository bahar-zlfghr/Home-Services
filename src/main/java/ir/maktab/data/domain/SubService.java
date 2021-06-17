package ir.maktab.data.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class SubService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Long basePrice;

    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "SUB_SERVICE_SERVICE_FK"))
    private Service service;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Specialist> specialists;

    public Integer getId() {
        return id;
    }

    public SubService setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubService setName(String name) {
        this.name = name;
        return this;
    }

    public Long getBasePrice() {
        return basePrice;
    }

    public SubService setBasePrice(Long basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SubService setDescription(String description) {
        this.description = description;
        return this;
    }

    public Service getService() {
        return service;
    }

    public SubService setService(Service service) {
        this.service = service;
        return this;
    }

    public Set<Specialist> getSpecialists() {
        return specialists;
    }

    public SubService setSpecialists(Set<Specialist> specialists) {
        this.specialists = specialists;
        return this;
    }
}
