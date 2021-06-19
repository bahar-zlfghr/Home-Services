package ir.maktab.data.repository.comment;

import ir.maktab.data.domain.Comment;
import ir.maktab.data.domain.Customer;
import ir.maktab.data.domain.Order;
import ir.maktab.data.domain.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
import java.util.Optional;

/**
 * @author : Bahar Zolfaghari
 **/
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("UPDATE Comment AS c SET c.score = :score WHERE c.id = :id")
    void updateScoreComment(@Param("id") Integer id, @Param("score") Integer score);

    @Query("UPDATE Comment AS c SET c.description = :description WHERE c.id = :id")
    void updateDescriptionComment(@Param("id") Integer id, @Param("description") String description);

    Set<Comment> getCommentsByCustomer(Customer customer);
    Optional<Comment> getCommentByOrder(Order order);
    Set<Comment> getCommentsBySpecialist(Specialist specialist);
}
