package ir.maktab.web.manager;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.data.enums.PersonRole;
import ir.maktab.dtos.ServiceDto;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.dtos.SubServiceDto;
import ir.maktab.dtos.filter.UserFilterDto;
import ir.maktab.dtos.filter.UserFilterResult;
import ir.maktab.exceptions.*;
import ir.maktab.service.manager.ManagerService;
import ir.maktab.service.service.ServiceService;
import ir.maktab.service.specialist.SpecialistService;
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
    private final SpecialistService specialistService;

    public ManagerController(ManagerService managerService, UserService userService, ServiceService serviceService, SubServiceService subServiceService, SpecialistService specialistService) {
        this.managerService = managerService;
        this.userService = userService;
        this.serviceService = serviceService;
        this.subServiceService = subServiceService;
        this.specialistService = specialistService;
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

    @GetMapping("/create/subservice")
    public ModelAndView createSubServiceForm() {

        return new ModelAndView("/manager/createSubService",
                "services", serviceService.getAllService());
    }

    @PostMapping("/create/subservice")
    public String createService(@RequestParam(value = "serviceName") String serviceName,
                                @RequestParam(value = "subServiceName") String subServiceName,
                                @RequestParam(value = "basePrice") String basePrice,
                                @RequestParam(value = "description") String description,
                                Model model) throws DuplicateSubServiceNameException, NotFoundServiceException {
        ServiceDto serviceDto = serviceService.getServiceByName(serviceName);

        SubServiceDto subServiceDto = new SubServiceDto()
                .setName(subServiceName)
                .setBasePrice(Long.valueOf(basePrice))
                .setDescription(description)
                .setServiceDto(serviceDto);

        subServiceService.saveSubService(subServiceDto);

        model.addAttribute("service", serviceDto);
        model.addAttribute("subService", subServiceDto);

        return "/manager/subServiceSaveSuccess";
    }

    @GetMapping("/add/specialist/subservice")
    public ModelAndView assignSpecialistToSubServiceForm() {
        ModelAndView modelAndView = new ModelAndView("/manager/assignSpecialistToSubService");

        modelAndView.getModel().put("subServices", subServiceService.getAllSubServices());
        modelAndView.getModel().put("specialists", specialistService.getAllSpecialists());

        return modelAndView;
    }

    @PostMapping("/add/specialist/subservice")
    public String assignSpecialistToSubServiceForm(@RequestParam(value = "subServiceName") String subServiceName,
                                                   @RequestParam(value = "specialistEmail") String specialistEmail,
                                                   Model model) throws NotFoundSubServiceException, NotFoundUserException {

        SubServiceDto subServiceDto = subServiceService.getSubServiceByName(subServiceName);
        SpecialistDto specialistDto = specialistService.getSpecialistByEmail(specialistEmail);

        specialistDto.getSubServiceDtos().add(subServiceDto);
        subServiceDto.getSpecialistDtos().add(specialistDto);

        subServiceService.updateSubServiceSpecialists(subServiceDto);

        model.addAttribute("subService", subServiceDto);
        model.addAttribute("specialist", specialistDto);

        return "/manager/assignSpecialistToSubServiceSuccess";
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getModel());
    }

    @ExceptionHandler(value = DuplicateServiceNameException.class)
    public ModelAndView duplicateServiceNameErrorHandler(DuplicateServiceNameException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);

        Map<String, Object> model = new HashMap<>();
        model.put("duplicateServiceName", ex.getMessage());
        model.put("serviceDto", new ServiceDto());

        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = DuplicateSubServiceNameException.class)
    public ModelAndView duplicateSubServiceNameErrorHandler(DuplicateSubServiceNameException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);

        Map<String, Object> model = new HashMap<>();
        model.put("duplicateSubServiceName", ex.getMessage());
        model.put("subServiceDto", new SubServiceDto());

        return new ModelAndView(lastView, model);
    }
}
