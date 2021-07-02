package ir.maktab.data.repository.customer;

import ir.maktab.data.domain.Customer;
import ir.maktab.data.domain.Order;
import ir.maktab.data.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    @Modifying
    @Query("UPDATE Customer AS c SET c.status = :status WHERE c.id = :id")
    void updateCustomerStatus(@Param("id") Integer id, @Param("status") UserStatus status);

    @Query("UPDATE Customer AS c SET c.orders = :orders WHERE c.id = :id")
    void updateCustomerOrders(@Param("id") Integer id, @Param("orders") Set<Order> orders);

    @Query("UPDATE Customer AS c SET c.password = :newPassword WHERE c.email = :email AND c.password = :previousPassword")
    void updateCustomerPassword(@Param("email") String email, @Param("previousPassword") String previousPassword, @Param("newPassword") String newPassword);

    Optional<Customer> getCustomerByEmail(String email);
    Optional<Customer> getCustomerByEmailAndPassword(String email, String password);
}
