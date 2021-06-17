package ir.maktab.data.domain;

import javax.persistence.*;

/**
 * @author : Bahar Zolfaghari
 **/
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Long balance;

    public Integer getId() {
        return id;
    }

    public Account setId(Integer id) {
        this.id = id;
        return this;
    }

    public Long getBalance() {
        return balance;
    }

    public Account setBalance(Long balance) {
        this.balance = balance;
        return this;
    }
}
