package ir.maktab.service.user;

import ir.maktab.dtos.UserDto;
import ir.maktab.dtos.filter.UserFilterDto;

import java.util.Set;

/**
 * @author : Bahar Zolfaghari
 **/
public interface UserFilter {

    Set<UserDto> filterUsers(UserFilterDto userFilterDto);
}
