package ir.maktab.data.repository.account;

import ir.maktab.data.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Repository
public interface AccountRepository extends JpaRepository<Wallet, Integer> {

    @Query("UPDATE Wallet AS a SET a.balance = :balance WHERE a.id = :id")
    void updateAccount(@Param("id") Integer id, @Param("balance") Long balance);

    Optional<Wallet> getAccountById(Integer id);
}
