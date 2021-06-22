package ir.maktab.service.user;

import ir.maktab.dtos.filter.UserFilterDto;
import ir.maktab.dtos.filter.UserFilterResult;

/**
 * @author : Bahar Zolfaghari
 **/
public interface UserService {

    UserFilterResult filterUsers(UserFilterDto userFilterDto);
}
