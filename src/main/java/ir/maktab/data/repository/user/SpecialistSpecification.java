package ir.maktab.data.repository.user;

import ir.maktab.data.domain.Specialist;
import ir.maktab.dtos.filter.UserFilterDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Bahar Zolfaghari
 **/
public interface SpecialistSpecification {

    static Specification<Specialist> filterSpecialists(UserFilterDto userFilterDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            CriteriaQuery<Specialist> specialistCriteriaQuery = criteriaBuilder.createQuery(Specialist.class);

            if (userFilterDto.getRole() != null) {
                predicates.add(criteriaBuilder.equal(root.get("role"), userFilterDto.getRole()));
            }

            if (userFilterDto.getName() != null && !userFilterDto.getName().equals("")) {
                predicates.add(criteriaBuilder.equal(root.get("name"), "bahar"));
            }

            if (userFilterDto.getFamily() != null && !userFilterDto.getFamily().equals("")) {
                predicates.add(criteriaBuilder.equal(root.get("family"), userFilterDto.getFamily()));
            }

            if (userFilterDto.getEmail() != null && !userFilterDto.getEmail().equals("")) {
                predicates.add(criteriaBuilder.equal(root.get("email"), userFilterDto.getEmail()));
            }

            if (userFilterDto.getSpeciality() != null && !userFilterDto.getSpeciality().equals("")) {
                predicates.add(criteriaBuilder.equal(root.get("specialty"), userFilterDto.getSpeciality()));
            }

            if (userFilterDto.getScore() != null && !userFilterDto.getScore().equals("")) {
                predicates.add(criteriaBuilder.equal(root.get("score"), Integer.parseInt(userFilterDto.getScore())));
            }

            return specialistCriteriaQuery.select(root).where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }
}
