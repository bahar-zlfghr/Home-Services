package ir.maktab.data.domain;

import ir.maktab.data.enums.UserStatus;

import javax.persistence.*;

/**
 * @author : Bahar Zolfaghari
 **/
@MappedSuperclass
public class User extends Person {
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id")
    private Account account;

    public UserStatus getStatus() {
        return status;
    }

    public User setStatus(UserStatus status) {
        this.status = status;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public User setAccount(Account account) {
        this.account = account;
        return this;
    }
}
