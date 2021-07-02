package ir.maktab.data.repository.user;

import ir.maktab.data.domain.ConfirmationToken;
import ir.maktab.data.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByConfirmationToken(ConfirmationToken confirmationToken);
}
