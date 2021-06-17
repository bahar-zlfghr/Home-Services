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

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "specialist_id", foreignKey = @ForeignKey(name = "PROFILE_PICTURE_SPECIALIST_FK"))
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
