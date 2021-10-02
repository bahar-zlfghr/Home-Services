package ir.maktab.dtos;

/**
 * @author : Bahar Zolfaghari
 **/
public class WalletDto {
    private Integer id;
    private Long balance;

    public Integer getId() {
        return id;
    }

    public WalletDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Long getBalance() {
        return balance;
    }

    public WalletDto setBalance(Long balance) {
        this.balance = balance;
        return this;
    }
}
