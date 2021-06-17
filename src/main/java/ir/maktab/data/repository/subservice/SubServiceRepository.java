package ir.maktab.data.repository.subservice;

import ir.maktab.data.domain.Service;
import ir.maktab.data.domain.Specialist;
import ir.maktab.data.domain.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface SubServiceRepository extends JpaRepository<SubService, Integer> {

    @Query("UPDATE SubService AS s SET s.name = :name WHERE s.id = :id")
    void updateSubServiceName(@Param("id") Integer id, @Param("name") String name);

    @Query("UPDATE SubService AS s SET s.basePrice = :basePrice WHERE s.id = :id")
    void updateSubServiceBasePrice(@Param("id") Integer id, @Param("basePrice") Long basePrice);

    @Query("UPDATE SubService AS s SET s.description = :description WHERE s.id = :id")
    void updateSubServiceDescription(@Param("id") Integer id, @Param("description") String description);

    @Query("UPDATE SubService AS s SET s.specialists = :specialists WHERE s.id = :subServiceId")
    void updateSubServiceSpecialists(@Param("subServiceId") Integer subServiceId, @Param("specialists") Set<Specialist> specialists);

    Optional<SubService> getSubServiceByName(String name);

    Set<SubService> getSubServicesByService(Service service);
}
