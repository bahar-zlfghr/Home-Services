package ir.maktab.data.repository.specialist;

import ir.maktab.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Integer>, JpaSpecificationExecutor<Specialist> {

    @Query("UPDATE Specialist AS s SET s.score = :score WHERE s.id = :id")
    void updateSpecialistScore(@Param("id") Integer id, @Param("score") Integer score);

    @Query("UPDATE Specialist AS s SET s.profilePicture = :profilePicture WHERE s.id = :id")
    void updateSpecialistProfilePicture(@Param("id") Integer id, @Param("profilePicture") byte[] profilePicture);

    @Query("UPDATE Specialist AS s SET s.subServices = :subServices WHERE s.id = :id")
    void updateSpecialistSubServices(@Param("id") Integer id, @Param("subServices") Set<SubService> subServices);

    @Query("UPDATE Specialist AS s SET s.services = :services WHERE s.id = :id")
    void updateSpecialistServices(@Param("id") Integer id, @Param("services") Set<Service> services);

    @Query("UPDATE Specialist AS s SET s.offers = :offers WHERE s.id = :id")
    void updateSpecialistOffers(@Param("id") Integer id, @Param("offers") Set<Offer> offers);

    @Query("UPDATE Specialist AS s SET s.orders = :orders WHERE s.id = :id")
    void updateSpecialistOrders(@Param("id") Integer id, @Param("orders") Set<Order> orders);

    @Query("UPDATE Specialist AS s SET s.password = :newPassword WHERE s.email = :email AND s.password = :previousPassword")
    void updateSpecialistPassword(@Param("email") String email, @Param("previousPassword") String previousPassword, @Param("newPassword") String newPassword);

    Optional<Specialist> getSpecialistByEmail(String email);
    Optional<Specialist> getSpecialistByEmailAndPassword(String email, String password);

    @Query("FROM Specialist")
    Set<Specialist> getAllSpecialists();
}
