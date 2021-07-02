package ir.maktab.web.manager;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dtos.*;
import ir.maktab.dtos.creator.SubserviceCreator;
import ir.maktab.dtos.filter.UserFilterDto;
import ir.maktab.dtos.filter.UserFilterResult;
import ir.maktab.exceptions.*;
import ir.maktab.service.manager.ManagerService;
import ir.maktab.service.service.ServiceService;
import ir.maktab.service.specialist.SpecialistService;
import ir.maktab.service.subservice.SubServiceService;
import ir.maktab.service.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/admin")
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

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("")
    public String manager() {
        return "/manager/manager";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/filter-users")
    public ModelAndView filterUsersForm() {
        return new ModelAndView(
                "/manager/filterUser",
                "userFilterDto", new UserFilterDto());
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/filter-users")
    public ModelAndView filterUsers(@ModelAttribute("userFilterDto") UserFilterDto userFilterDto) {
        UserFilterResult result = userService.filterUsers(userFilterDto);
        return new ModelAndView(
                "/manager/filterUser",
                "users", result);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/create-service")
    public ModelAndView createServiceForm() {
        ModelAndView modelAndView = new ModelAndView(
                "/manager/createService");
        modelAndView.addObject("serviceDto", new ServiceDto());
        modelAndView.addObject("successCreateService", false);
        return modelAndView;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/create-service")
    public String createService(@ModelAttribute("serviceDto") @Valid ServiceDto serviceDto,
                                Model model) throws DuplicateServiceNameException {
        serviceService.saveService(serviceDto);
        model.addAttribute("successCreateService", true);
        model.addAttribute("serviceDto", new ServiceDto());
        return "/manager/createService";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/create-sub-service")
    public ModelAndView createSubServiceForm() {
        ModelAndView modelAndView = new ModelAndView("/manager/createSubService");
        modelAndView.addObject("services", serviceService.getAllServices());
        modelAndView.addObject("successCreateSubService", false);
        return modelAndView;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/create-sub-service")
    public String createService(@RequestParam(value = "serviceName") String serviceName,
                                @RequestParam(value = "subServiceName") String subServiceName,
                                @RequestParam(value = "basePrice") String basePrice,
                                @RequestParam(value = "description") String description,
                                Model model) throws DuplicateSubServiceNameException, NotFoundServiceException {
        ServiceDto serviceDto = serviceService.getServiceByName(serviceName);
        SubServiceDto subServiceDto = SubserviceCreator.createSubService(serviceDto, subServiceName, basePrice, description);
        subServiceService.saveSubService(subServiceDto);
        model.addAttribute("successCreateSubService", true);
        model.addAttribute("services", serviceService.getAllServices());
        return "/manager/createSubService";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/add-specialist-sub-service")
    public ModelAndView assignSpecialistToSubServiceForm() {
        ModelAndView modelAndView = new ModelAndView("/manager/assignSpecialistToSubService");
        modelAndView.addObject("successAssignSpecialistToSubService", false);
        modelAndView.getModel().put("subServices", subServiceService.getAllSubServices());
        modelAndView.getModel().put("specialists", specialistService.getAllSpecialists());
        return modelAndView;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/add-specialist-sub-service")
    public String assignSpecialistToSubServiceForm(@RequestParam(value = "subServiceName") String subServiceName,
                                                   @RequestParam(value = "specialistEmail") String specialistEmail,
                                                   Model model) throws NotFoundSubServiceException, NotFoundUserException, SubServiceAlreadyProvidedBySpecialistException {
        SubServiceDto subServiceDto = subServiceService.getSubServiceByName(subServiceName);
        SpecialistDto specialistDto = specialistService.getSpecialistByEmail(specialistEmail);
        subServiceService.updateSubServiceSpecialists(subServiceDto, specialistDto);
        model.addAttribute("successAssignSpecialistToSubService", true);
        model.addAttribute("subServices", subServiceService.getAllSubServices());
        model.addAttribute("specialists", specialistService.getAllSpecialists());
        return "/manager/assignSpecialistToSubService";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/add-specialist-service")
    public ModelAndView assignSpecialistToServiceForm() {
        ModelAndView modelAndView = new ModelAndView("/manager/assignSpecialistToService");
        modelAndView.addObject("successAssignSpecialistToService", false);
        modelAndView.getModel().put("services", serviceService.getAllServices());
        modelAndView.getModel().put("specialists", specialistService.getAllSpecialists());
        return modelAndView;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/add-specialist-service")
    public String assignSpecialistToServiceForm(@RequestParam(value = "serviceName") String serviceName,
                                                @RequestParam(value = "specialistEmail") String specialistEmail,
                                                Model model) throws NotFoundServiceException, NotFoundUserException, ServiceAlreadyProvidedBySpecialistException {
        ServiceDto serviceDto = serviceService.getServiceByName(serviceName);
        SpecialistDto specialistDto = specialistService.getSpecialistByEmail(specialistEmail);
        serviceService.updateServiceSpecialists(serviceDto, specialistDto);
        model.addAttribute("successAssignSpecialistToService", true);
        model.addAttribute("services", subServiceService.getAllSubServices());
        model.addAttribute("specialists", specialistService.getAllSpecialists());
        return "/manager/assignSpecialistToService";
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getModel());
    }

    @ExceptionHandler(value = DuplicateServiceNameException.class)
    public ModelAndView duplicateServiceNameExceptionHandler(DuplicateServiceNameException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        model.put("successCreateService", false);
        model.put("duplicateServiceName", ex.getMessage());
        model.put("services", serviceService.getAllServices());
        model.put("serviceDto", new ServiceDto());
        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = DuplicateSubServiceNameException.class)
    public ModelAndView duplicateSubServiceNameExceptionHandler(DuplicateSubServiceNameException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        model.put("successCreateSubService", false);
        model.put("duplicateSubServiceName", ex.getMessage());
        model.put("subServices", subServiceService.getAllSubServices());
        model.put("specialists", specialistService.getAllSpecialists());
        model.put("subServiceDto", new SubServiceDto());
        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = SubServiceAlreadyProvidedBySpecialistException.class)
    public ModelAndView subServiceAlreadyProvidedBySpecialistExceptionHandler(SubServiceAlreadyProvidedBySpecialistException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        model.put("successAssignSpecialistToSubService", false);
        model.put("subServiceAlreadyProvidedBySpecialist", ex.getMessage());
        model.put("subServices", subServiceService.getAllSubServices());
        model.put("specialists", specialistService.getAllSpecialists());
        model.put("subServiceDto", new SubServiceDto());
        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = ServiceAlreadyProvidedBySpecialistException.class)
    public ModelAndView serviceAlreadyProvidedBySpecialistExceptionHandler(ServiceAlreadyProvidedBySpecialistException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        model.put("successAssignSpecialistToService", false);
        model.put("serviceAlreadyProvidedBySpecialist", ex.getMessage());
        model.put("services", serviceService.getAllServices());
        model.put("specialists", specialistService.getAllSpecialists());
        model.put("serviceDto", new SubServiceDto());
        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = NotFoundUserException.class)
    public ModelAndView notFoundUserExceptionHandler(NotFoundUserException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        model.put("loginError", ex.getMessage());
        model.put("managerDto", new ManagerDto());
        request.getSession().setAttribute("fail", true);
        return new ModelAndView(lastView, model);
    }
}
