package ir.maktab.dtos;

import java.util.Date;

/**
 * @author : Bahar Zolfaghari
 **/
public class ConfirmationTokenDto {
    private Integer id;
    private String token;
    private Date createdDate;
    private boolean enabled;

    public Integer getId() {
        return id;
    }

    public ConfirmationTokenDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getToken() {
        return token;
    }

    public ConfirmationTokenDto setToken(String token) {
        this.token = token;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public ConfirmationTokenDto setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public ConfirmationTokenDto setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
