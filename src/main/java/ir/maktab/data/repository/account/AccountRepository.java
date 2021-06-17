package ir.maktab.data.repository.account;

import ir.maktab.data.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("UPDATE Account AS a SET a.balance = :balance WHERE a.id = :id")
    void updateAccount(@Param("id") Integer id, @Param("balance") Long balance);

    Optional<Account> getAccountById(Integer id);
}
