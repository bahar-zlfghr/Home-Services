package ir.maktab.data.repository.user;

import ir.maktab.data.domain.Customer;
import ir.maktab.data.domain.Specialist;
import ir.maktab.data.domain.User;
import ir.maktab.dtos.filter.UserFilterDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Bahar Zolfaghari
 **/
public interface UserSpecification extends JpaRepository<User, Integer>, JpaSpecificationExecutor<Customer> {

    static Specification<Customer> filterCustomers(UserFilterDto userFilterDto) {
        return (Specification<Customer>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            CriteriaQuery<Customer> customerCriteriaQuery = criteriaBuilder.createQuery(Customer.class);

            if (userFilterDto.getRole() != null) {
                predicates.add(criteriaBuilder.equal(root.get("role"), userFilterDto.getRole()));
            }

            if (userFilterDto.getName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), userFilterDto.getName()));
            }

            if (userFilterDto.getFamily() != null) {
                predicates.add(criteriaBuilder.equal(root.get("family"), userFilterDto.getFamily()));
            }

            if (userFilterDto.getEmail() != null) {
                predicates.add(criteriaBuilder.equal(root.get("email"), userFilterDto.getEmail()));
            }

            return customerCriteriaQuery.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }

    static Specification<Specialist> filterSpecialists(UserFilterDto userFilterDto) {
        return (Specification<Specialist>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            CriteriaQuery<Specialist> specialistCriteriaQuery = criteriaBuilder.createQuery(Specialist.class);

            if (userFilterDto.getRole() != null) {
                predicates.add(criteriaBuilder.equal(root.get("role"), userFilterDto.getRole()));
            }

            if (userFilterDto.getName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), userFilterDto.getName()));
            }

            if (userFilterDto.getFamily() != null) {
                predicates.add(criteriaBuilder.equal(root.get("family"), userFilterDto.getFamily()));
            }

            if (userFilterDto.getEmail() != null) {
                predicates.add(criteriaBuilder.equal(root.get("email"), userFilterDto.getEmail()));
            }

            if (userFilterDto.getSpeciality() != null) {
                predicates.add(criteriaBuilder.equal(root.get("specialty"), userFilterDto.getSpeciality()));
            }

            return specialistCriteriaQuery.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }
}
