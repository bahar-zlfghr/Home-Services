package ir.maktab.data.repository.user;

import ir.maktab.data.domain.Customer;
import ir.maktab.dtos.filter.UserFilterDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Bahar Zolfaghari
 **/
public interface CustomerSpecification {

    static Specification<Customer> filterCustomers(UserFilterDto userFilterDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            CriteriaQuery<Customer> customerCriteriaQuery = criteriaBuilder.createQuery(Customer.class);

            if (userFilterDto.getRole() != null) {
                predicates.add(criteriaBuilder.equal(root.get("role"), userFilterDto.getRole()));
            }

            if (userFilterDto.getName() != null && !userFilterDto.getName().equals("")) {
                predicates.add(criteriaBuilder.equal(root.get("name"), userFilterDto.getName()));
            }

            if (userFilterDto.getFamily() != null && !userFilterDto.getFamily().equals("")) {
                predicates.add(criteriaBuilder.equal(root.get("family"), userFilterDto.getFamily()));
            }

            if (userFilterDto.getEmail() != null && !userFilterDto.getEmail().equals("")) {
                predicates.add(criteriaBuilder.equal(root.get("email"), userFilterDto.getEmail()));
            }

            return customerCriteriaQuery.select(root).where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }
}
