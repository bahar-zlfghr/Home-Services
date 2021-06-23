package ir.maktab.data.repository.service;

import ir.maktab.data.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

    @Query("UPDATE Service AS s SET s.name = :name WHERE s.id = :id")
    void UpdateServiceName(@Param("id") Integer id, @Param("name") String name);

    Optional<Service> getServiceByName(String name);

    @Query("FROM Service")
    Set<Service> getAllServices();
}
