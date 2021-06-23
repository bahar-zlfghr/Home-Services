package ir.maktab.web.manager;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.data.enums.PersonRole;
import ir.maktab.dtos.ServiceDto;
import ir.maktab.dtos.filter.UserFilterDto;
import ir.maktab.dtos.filter.UserFilterResult;
import ir.maktab.exceptions.DuplicateServiceNameException;
import ir.maktab.service.manager.ManagerService;
import ir.maktab.service.service.ServiceService;
import ir.maktab.service.subservice.SubServiceService;
import ir.maktab.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Bahar Zolfaghari
 **/
@Controller
@RequestMapping("/administrator")
public class ManagerController {
    private final ManagerService managerService;
    private final UserService userService;
    private final ServiceService serviceService;
    private final SubServiceService subServiceService;

    public ManagerController(ManagerService managerService, UserService userService, ServiceService serviceService, SubServiceService subServiceService) {
        this.managerService = managerService;
        this.userService = userService;
        this.serviceService = serviceService;
        this.subServiceService = subServiceService;
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

    @GetMapping("/create/service")
    public ModelAndView createServiceForm() {

        return new ModelAndView(
                "/manager/createService",
                "serviceDto", new ServiceDto());
    }

    @PostMapping("/create/service")
    public String createService(@ModelAttribute("serviceDto") @Valid ServiceDto serviceDto,
                                Model model) throws DuplicateServiceNameException {
        serviceService.saveService(serviceDto);

        model.addAttribute("service", serviceDto);

        return "/manager/serviceSaveSuccess";
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getModel());
    }

    @ExceptionHandler(value = DuplicateServiceNameException.class)
    public ModelAndView DuplicateServiceNameErrorHandler(DuplicateServiceNameException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);

        Map<String, Object> model = new HashMap<>();
        model.put("duplicateServiceName", ex.getMessage());
        model.put("serviceDto", new ServiceDto());

        return new ModelAndView(lastView, model);
    }
}
