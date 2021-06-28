package ir.maktab.data.repository.specialist;

import ir.maktab.data.domain.Specialist;
import ir.maktab.data.enums.PersonRole;
import ir.maktab.dtos.filter.UserFilterDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author : Bahar Zolfaghari
 **/
public interface SpecialistSpecification {

    static Specification<Specialist> filterSpecialists(UserFilterDto userFilterDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            CriteriaQuery<Specialist> specialistCriteriaQuery = criteriaBuilder.createQuery(Specialist.class);
            if (!Objects.isNull(userFilterDto.getRole()) && !userFilterDto.getRole().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("role"), PersonRole.valueOf(userFilterDto.getRole().toUpperCase())));
            }
            if (!Objects.isNull(userFilterDto.getName()) && !userFilterDto.getName().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("name"), userFilterDto.getName()));
            }
            if (!Objects.isNull(userFilterDto.getFamily()) && !userFilterDto.getFamily().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("family"), userFilterDto.getFamily()));
            }
            if (!Objects.isNull(userFilterDto.getEmail()) && !userFilterDto.getEmail().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("email"), userFilterDto.getEmail()));
            }
            if (!Objects.isNull(userFilterDto.getScore()) && !userFilterDto.getScore().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("score"), Integer.parseInt(userFilterDto.getScore())));
            }
            return specialistCriteriaQuery.select(root).where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }
}
