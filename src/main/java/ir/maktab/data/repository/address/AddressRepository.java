package ir.maktab.data.repository.address;

import ir.maktab.data.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("UPDATE Address AS a SET a.city = :city WHERE a.id = :id")
    void updateCityAddress(@Param("id") Integer id, @Param("city") String city);

    @Query("UPDATE Address AS a SET a.state = :state WHERE a.id = :id")
    void updateStateAddress(@Param("id") Integer id, @Param("state") String state);

    @Query("UPDATE Address AS a SET a.formattedAddress = :formatted_address WHERE a.id = :id")
    void updateAddress(@Param("id") Integer id, @Param("formatted_address") String formatted_address);

    Set<Address> getAddressesByCity(String city);
    Set<Address> getAddressesByState(String state);
}
