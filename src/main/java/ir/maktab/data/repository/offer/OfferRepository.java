package ir.maktab.data.repository.offer;

import ir.maktab.data.domain.Offer;
import ir.maktab.data.domain.Order;
import ir.maktab.data.domain.Specialist;
import ir.maktab.data.enums.OfferStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface OfferRepository extends JpaRepository<Offer, Integer>, JpaSpecificationExecutor<Offer> {

    @Query("UPDATE Offer AS o SET o.suggestedPrice = :suggestedPrice WHERE o.id = :id")
    void updateOfferSuggestedPrice(@Param("id") Integer id, @Param("suggestedPrice") Long suggestedPrice);

    @Query("UPDATE Offer AS o SET o.status = :status WHERE o.id = :id")
    void updateOfferStatus(@Param("id") Integer id, @Param("status") OfferStatus offerStatus);

    Set<Offer> getOffersBySpecialist(Specialist specialist);

    Set<Offer> getOffersByOrder(Order order);

    static Specification<Offer> getSortedOffersBySuggestedPriceOrScore(boolean suggestedPrice, boolean score) {
        return (Specification<Offer>) (root, query, criteriaBuilder) -> {

            CriteriaQuery<Offer> criteriaQuery = criteriaBuilder.createQuery(Offer.class);

            Root<Offer> from = criteriaQuery.from(Offer.class);
            CriteriaQuery<Offer> select = criteriaQuery.select(from);

            if (suggestedPrice) {
                criteriaQuery.orderBy(criteriaBuilder.asc(from.get("suggestedPrice")));
            }
            if (score) {
                criteriaQuery.orderBy(criteriaBuilder.asc(from.get("specialist").get("score")));
            }

            return criteriaQuery.getRestriction();
        };
    }
}
