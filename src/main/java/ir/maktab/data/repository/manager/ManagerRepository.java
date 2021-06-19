package ir.maktab.data.repository.manager;

import ir.maktab.data.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    @Query("UPDATE Manager AS m SET m.name = :name WHERE m.id = :id")
    void updateManagerName(@Param("id") Integer id, @Param("name") String name);

    @Query("UPDATE Manager AS m SET m.family = :family WHERE m.id = :id")
    void updateManagerFamily(@Param("id") Integer id, @Param("family") String family);

    @Query("UPDATE Manager AS m SET m.email = :email WHERE m.id = :id")
    void updateManagerEmail(@Param("id") Integer id, @Param("email") String email);

    @Query("UPDATE Manager AS m SET m.password = :newPassword WHERE m.email = :email AND m.password = :previousPassword")
    void updateManagerPassword(@Param("email") String email, @Param("previousPassword") String previousPassword, @Param("newPassword") String newPassword);

    Optional<Manager> findManagerByEmail(String email);
    Optional<Manager> getManagerByEmailAndPassword(String email, String password);
}
