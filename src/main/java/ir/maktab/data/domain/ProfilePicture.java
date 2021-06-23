package ir.maktab.data.domain;

import javax.persistence.*;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class ProfilePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] data;

    @OneToOne(mappedBy = "profilePicture", cascade = {CascadeType.ALL, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Specialist specialist;

    public Integer getId() {
        return id;
    }

    public ProfilePicture setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProfilePicture setName(String name) {
        this.name = name;
        return this;
    }

    public byte[] getData() {
        return data;
    }

    public ProfilePicture setData(byte[] data) {
        this.data = data;
        return this;
    }

    public Specialist getSpecialist() {
        return specialist;
    }

    public ProfilePicture setSpecialist(Specialist specialist) {
        this.specialist = specialist;
        return this;
    }
}
