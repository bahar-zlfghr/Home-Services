package ir.maktab.web.manager;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dtos.ServiceDto;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.dtos.SubServiceDto;
import ir.maktab.dtos.factory.SubserviceFactory;
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

    @GetMapping("/filter")
    public ModelAndView filterUsersForm() {
        return new ModelAndView(
                "/manager/filterUser",
                "userFilterDto", new UserFilterDto());
    }

    @PostMapping("/filter")
    public ModelAndView filterUsers(@ModelAttribute("userFilterDto") UserFilterDto userFilterDto) {
        //ToDo: add subservice property
        //ToDo: pagination
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
        model.addAttribute("success", true);
        return "/manager/createService";
    }

    @GetMapping("/create/subservice")
    public ModelAndView createSubServiceForm() {
        return new ModelAndView(
                "/manager/createSubService",
                "services", serviceService.getAllServices());
    }

    @PostMapping("/create/subservice")
    public String createService(@RequestParam(value = "serviceName") String serviceName,
                                @RequestParam(value = "subServiceName") String subServiceName,
                                @RequestParam(value = "basePrice") String basePrice,
                                @RequestParam(value = "description") String description,
                                Model model) throws DuplicateSubServiceNameException, NotFoundServiceException {
        ServiceDto serviceDto = serviceService.getServiceByName(serviceName);
        SubServiceDto subServiceDto = SubserviceFactory.createSubService(serviceDto, subServiceName, basePrice, description);
        subServiceService.saveSubService(subServiceDto);
        model.addAttribute("success", true);
        model.addAttribute("services", serviceService.getAllServices());
        return "/manager/createSubService";
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
                                                   Model model) throws NotFoundSubServiceException, NotFoundUserException, SubServiceAlreadyProvidedBySpecialistException {
        SubServiceDto subServiceDto = subServiceService.getSubServiceByName(subServiceName);
        SpecialistDto specialistDto = specialistService.getSpecialistByEmail(specialistEmail);
        subServiceService.updateSubServiceSpecialists(subServiceDto, specialistDto);
        model.addAttribute("success", true);
        model.addAttribute("subServices", subServiceService.getAllSubServices());
        model.addAttribute("specialists", specialistService.getAllSpecialists());
        return "/manager/assignSpecialistToSubService";
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
        model.put("duplicateServiceName", ex.getMessage());
        model.put("services", serviceService.getAllServices());
        model.put("serviceDto", new ServiceDto());
        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = DuplicateSubServiceNameException.class)
    public ModelAndView duplicateSubServiceNameExceptionHandler(DuplicateSubServiceNameException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
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
        model.put("subServiceAlreadyProvidedBySpecialist", ex.getMessage());
        model.put("subServices", subServiceService.getAllSubServices());
        model.put("specialists", specialistService.getAllSpecialists());
        model.put("subServiceDto", new SubServiceDto());
        return new ModelAndView(lastView, model);
    }
}
