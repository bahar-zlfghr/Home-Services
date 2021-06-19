package ir.maktab.data.repository.offer;

import ir.maktab.data.domain.Offer;
import ir.maktab.data.domain.Order;
import ir.maktab.data.domain.Specialist;
import ir.maktab.data.enums.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    @Query("UPDATE Offer AS o SET o.suggestedPrice = :suggestedPrice WHERE o.id = :id")
    void updateOfferSuggestedPrice(@Param("id") Integer id, @Param("suggestedPrice") Long suggestedPrice);

    @Query("UPDATE Offer AS o SET o.status = :status WHERE o.id = :id")
    void updateOfferStatus(@Param("id") Integer id, @Param("status") OfferStatus offerStatus);

    Set<Offer> getOffersBySpecialist(Specialist specialist);
    Set<Offer> getOffersByOrder(Order order);
}
