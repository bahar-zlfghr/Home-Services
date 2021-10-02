package ir.maktab.service.manager;

import ir.maktab.dtos.ManagerDto;
import ir.maktab.exceptions.NotFoundUserException;

/**
 * @author : Bahar Zolfaghari
 **/
public interface ManagerService {

    void saveManager(ManagerDto managerDto);
    void updateManagerName(Integer id, String name);
    void updateManagerFamily(Integer id, String family);
    void updateManagerEmail(Integer id, String email);
    void updateManagerPassword(String email, String previousPassword, String newPassword);
    void deleteManager(ManagerDto managerDto);
    ManagerDto getManagerByEmailAndPassword(String email, String password) throws NotFoundUserException;
}
