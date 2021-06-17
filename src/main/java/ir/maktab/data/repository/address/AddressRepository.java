package ir.maktab.data.repository.address;

import ir.maktab.data.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author : Bahar Zolfaghari
 **/
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("UPDATE Address AS a SET a.city = :city WHERE a.id = :id")
    void updateCityAddress(@Param("id") Integer id, @Param("city") String city);

    @Query("UPDATE Address AS a SET a.state = :state WHERE a.id = :id")
    void updateStateAddress(@Param("id") Integer id, @Param("state") String state);

    @Query("UPDATE Address AS a SET a.formatted_address = :formatted_address WHERE a.id = :id")
    void updateAlleyAddress(@Param("id") Integer id, @Param("formatted_address") String formatted_address);

    List<Address> getAddressesByCity(String city);
    List<Address> getAddressesByState(String state);
}
