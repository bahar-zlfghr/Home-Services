package ir.maktab.data.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String token;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private boolean enabled;

    public ConfirmationToken() {

    }

    public Integer getId() {
        return id;
    }

    public ConfirmationToken setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getToken() {
        return token;
    }

    public ConfirmationToken setToken(String token) {
        this.token = token;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public ConfirmationToken setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public ConfirmationToken setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
