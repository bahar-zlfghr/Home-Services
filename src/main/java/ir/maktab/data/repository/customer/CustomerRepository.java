package ir.maktab.data.repository.customer;

import ir.maktab.data.domain.Customer;
import ir.maktab.data.domain.Order;
import ir.maktab.data.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("UPDATE Customer AS c SET c.status = :status WHERE c.id = :id")
    void updateCustomerStatus(@Param("id") Integer id, @Param("orders") UserStatus status);

    @Query("UPDATE Customer AS c SET c.orders = :orders WHERE c.id = :id")
    void updateCustomerOrders(@Param("id") Integer id, @Param("orders") Set<Order> orders);

    @Query("UPDATE Customer AS c SET c.password = :newPassword WHERE c.email = :email AND c.password = :previousPassword")
    void updateCustomerPassword(@Param("email") String email, @Param("previousPassword") String previousPassword, @Param("newPassword") String newPassword);

    Optional<Customer> getCustomerByEmail(String email);
}
