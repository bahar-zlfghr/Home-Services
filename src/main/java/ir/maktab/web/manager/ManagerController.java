package ir.maktab.web.manager;

import ir.maktab.data.enums.PersonRole;
import ir.maktab.dtos.filter.UserFilterDto;
import ir.maktab.dtos.filter.UserFilterResult;
import ir.maktab.service.manager.ManagerService;
import ir.maktab.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author : Bahar Zolfaghari
 **/
@Controller
@RequestMapping("/administrator")
public class ManagerController {
    private final ManagerService managerService;
    private final UserService userService;

    public ManagerController(ManagerService managerService, UserService userService) {
        this.managerService = managerService;
        this.userService = userService;
    }

    @GetMapping("/filter")
    public ModelAndView filterUsers(@RequestParam(value = "role", required = false) String role,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "family", required = false) String family,
                                    @RequestParam(value = "email", required = false) String email,
                                    @RequestParam(value = "specialty", required = false) String specialty,
                                    @RequestParam(value = "score", required = false) String score) {
        UserFilterDto userFilterDto = new UserFilterDto()
                .setName(name)
                .setFamily(family)
                .setEmail(email)
                .setSpeciality(specialty)
                .setScore(score);

        if (role != null && !role.equals("")) {
            userFilterDto.setRole(PersonRole.valueOf(role.toUpperCase()));
        }

        UserFilterResult result = userService.filterUsers(userFilterDto);
        return new ModelAndView(
                "/manager/filterUser",
                "users", result);
    }
}
