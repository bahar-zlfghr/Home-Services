package ir.maktab.dtos;

/**
 * @author : Bahar Zolfaghari
 **/
public class AccountDto {
    private Integer id;
    private Long balance;

    public Integer getId() {
        return id;
    }

    public AccountDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Long getBalance() {
        return balance;
    }

    public AccountDto setBalance(Long balance) {
        this.balance = balance;
        return this;
    }
}
