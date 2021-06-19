package ir.maktab.data.repository.offer;

import ir.maktab.data.domain.Offer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author : Bahar Zolfaghari
 **/
public interface OfferSpecification extends JpaSpecificationExecutor<Offer> {

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
