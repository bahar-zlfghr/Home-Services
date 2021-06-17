package ir.maktab.data.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "service", cascade = {CascadeType.ALL, CascadeType.PERSIST}, orphanRemoval = true)
    private Set<SubService> subServices = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public Service setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Service setName(String name) {
        this.name = name;
        return this;
    }

    public Set<SubService> getSubServices() {
        return subServices;
    }

    public Service setSubServices(Set<SubService> subServices) {
        this.subServices = subServices;
        return this;
    }
}
