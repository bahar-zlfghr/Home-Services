package ir.maktab.web.specialist;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dtos.ServiceDto;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.dtos.SubServiceDto;
import ir.maktab.dtos.creator.MailCreator;
import ir.maktab.dtos.creator.SpecialistCreator;
import ir.maktab.exceptions.*;
import ir.maktab.service.mail.EmailSenderService;
import ir.maktab.service.service.ServiceService;
import ir.maktab.service.specialist.SpecialistService;
import ir.maktab.service.subservice.SubServiceService;
import ir.maktab.service.token.ConfirmationTokenService;
import ir.maktab.validationgroup.RegistrationGroup;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Bahar Zolfaghari
 **/
@Controller
@RequestMapping(value = "/specialist")
public class SpecialistController {
    private final SpecialistService specialistService;
    private final ServiceService serviceService;
    private final SubServiceService subServiceService;
    private final EmailSenderService emailSenderService;
    private final ConfirmationTokenService confirmationTokenService;

    public SpecialistController(SpecialistService specialistService, ServiceService serviceService, SubServiceService subServiceService, EmailSenderService emailSenderService, ConfirmationTokenService confirmationTokenService) {
        this.specialistService = specialistService;
        this.serviceService = serviceService;
        this.subServiceService = subServiceService;
        this.emailSenderService = emailSenderService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @PreAuthorize("hasRole('SPECIALIST')")
    @GetMapping("")
    public String specialist() {
        return "/specialist/specialist";
    }

    @GetMapping("/register")
    public ModelAndView registrationForm() {
        ModelAndView modelAndView = new ModelAndView("/specialist/registration");
        modelAndView.addObject("specialistDto", new SpecialistDto());
        modelAndView.addObject("successRegister", false);
        return modelAndView;
    }

    @PostMapping("/register")
    public String registerSpecialist(@ModelAttribute("specialistDto") @Validated(RegistrationGroup.class) SpecialistDto specialistDto,
                                     @RequestParam("picture") CommonsMultipartFile picture,
                                     Model model) throws DuplicateEmailException, NotEmptyException {
        SpecialistDto specialist = SpecialistCreator.createSpecialist(specialistDto, picture);
        specialistService.saveSpecialist(specialist);
        emailSenderService.sendEmail(MailCreator.createEmail(specialistDto, specialist.getConfirmationTokenDto()));
        model.addAttribute("specialistEmail", specialistDto.getEmail());
        model.addAttribute("successRegister", true);
        model.addAttribute("specialistDto", new SpecialistDto());
        return "/specialist/registration";
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getModel());
    }

    @PreAuthorize("hasRole('SPECIALIST')")
    @GetMapping("/assign-sub-service")
    public ModelAndView assignSpecialistToSubServiceForm() {
        ModelAndView modelAndView = new ModelAndView("/specialist/assignToSubService");
        modelAndView.addObject("successAssignToSubService", false);
        modelAndView.getModel().put("subServices", subServiceService.getAllSubServices());
        return modelAndView;
    }

    @PreAuthorize("hasRole('SPECIALIST')")
    @PostMapping("/assign-sub-service")
    public String assignSpecialistToSubServiceForm(@RequestParam(value = "subServiceName") String subServiceName,
                                                   Model model,
                                                   Principal principal) throws NotFoundSubServiceException, NotFoundUserException, SubServiceAlreadyProvidedBySpecialistException {
        SubServiceDto subServiceDto = subServiceService.getSubServiceByName(subServiceName);
        SpecialistDto specialistDto = specialistService.getSpecialistByEmail(principal.getName());
        subServiceService.updateSubServiceSpecialists(subServiceDto, specialistDto);
        model.addAttribute("successAssignToSubService", true);
        model.addAttribute("subServices", subServiceService.getAllSubServices());
        return "/specialist/assignToSubService";
    }

    @PreAuthorize("hasRole('SPECIALIST')")
    @GetMapping("/assign-service")
    public ModelAndView assignSpecialistToServiceForm() {
        ModelAndView modelAndView = new ModelAndView("/specialist/assignToService");
        modelAndView.addObject("successAssignToService", false);
        modelAndView.getModel().put("services", serviceService.getAllServices());
        return modelAndView;
    }

    @PreAuthorize("hasRole('SPECIALIST')")
    @PostMapping("/assign-service")
    public String assignSpecialistToServiceForm(@RequestParam(value = "serviceName") String serviceName,
                                                Model model,
                                                Principal principal) throws NotFoundServiceException, NotFoundUserException, ServiceAlreadyProvidedBySpecialistException {
        ServiceDto serviceDto = serviceService.getServiceByName(serviceName);
        SpecialistDto specialistDto = specialistService.getSpecialistByEmail(principal.getName());
        serviceService.updateServiceSpecialists(serviceDto, specialistDto);
        model.addAttribute("successAssignToService", true);
        model.addAttribute("services", subServiceService.getAllSubServices());
        return "/specialist/assignToService";
    }

    @ExceptionHandler(value = DuplicateEmailException.class)
    public ModelAndView duplicateEmailExceptionHandler(DuplicateEmailException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        model.put("duplicateEmailError", ex.getMessage());
        model.put("specialistDto", new SpecialistDto());
        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = NotEmptyException.class)
    public ModelAndView notEmptyExceptionHandler(NotEmptyException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        model.put("emptyProfilePictureError", ex.getMessage());
        model.put("specialistDto", new SpecialistDto());
        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = SubServiceAlreadyProvidedBySpecialistException.class)
    public ModelAndView subServiceAlreadyProvidedBySpecialistExceptionHandler(SubServiceAlreadyProvidedBySpecialistException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        model.put("successAssignToSubService", false);
        model.put("subServiceAlreadyProvidedBySpecialist", ex.getMessage());
        model.put("subServices", subServiceService.getAllSubServices());
        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = ServiceAlreadyProvidedBySpecialistException.class)
    public ModelAndView serviceAlreadyProvidedBySpecialistExceptionHandler(ServiceAlreadyProvidedBySpecialistException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        model.put("successAssignToService", false);
        model.put("serviceAlreadyProvidedBySpecialist", ex.getMessage());
        model.put("services", serviceService.getAllServices());
        return new ModelAndView(lastView, model);
    }
}
