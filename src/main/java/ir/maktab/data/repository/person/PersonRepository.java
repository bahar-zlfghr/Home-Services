package ir.maktab.data.repository.person;

import ir.maktab.data.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByEmail(String email);

    @Modifying
    @Query("UPDATE Person AS p SET p.password = :newPassword WHERE p.email = :email AND p.password = :previousPassword")
    void updatePersonPassword(@Param("email") String email, @Param("previousPassword") String previousPassword, @Param("newPassword") String newPassword);
}
