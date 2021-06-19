package ir.maktab.data.repository.order;

import ir.maktab.data.domain.Address;
import ir.maktab.data.domain.Customer;
import ir.maktab.data.domain.Order;
import ir.maktab.data.domain.Specialist;
import ir.maktab.data.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("UPDATE Order AS o SET o.suggestedPrice = :suggestedPrice WHERE o.id = :id")
    void updateOrderSuggestedPrice(@Param("id") Integer id, @Param("suggestedPrice") Long suggestedPrice);

    @Query("UPDATE Order AS o SET o.description = :description WHERE o.id = :id")
    void updateOrderDescription(@Param("id") Integer id, @Param("description") String description);

    @Query("UPDATE Order AS o SET o.address = :address WHERE o.id = :id")
    void updateOrderAddress(@Param("id") Integer id, @Param("address") Address address);

    @Query("UPDATE Order AS o SET o.status = :orderStatus WHERE o.id = :id")
    void updateOrderStatus(@Param("id") Integer id, @Param("orderStatus") OrderStatus orderStatus);

    @Query("UPDATE Order AS o SET o.specialist = :specialist WHERE o.id = :id")
    void updateOrderSpecialist(@Param("id") Integer id, @Param("specialist") Specialist specialist);

    Set<Order> getOrdersByCustomer(Customer customer);
    Set<Order> getOrdersBySpecialist(Specialist specialist);
}
