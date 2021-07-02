package ir.maktab.data.domain;

import ir.maktab.data.enums.UserStatus;

import javax.persistence.*;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends Person {
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "confirm_token_id")
    private ConfirmationToken confirmationToken;

    public UserStatus getStatus() {
        return status;
    }

    public User setStatus(UserStatus status) {
        this.status = status;
        return this;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public User setWallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }

    public ConfirmationToken getConfirmationToken() {
        return confirmationToken;
    }

    public User setConfirmationToken(ConfirmationToken confirmationToken) {
        this.confirmationToken = confirmationToken;
        return this;
    }
}
