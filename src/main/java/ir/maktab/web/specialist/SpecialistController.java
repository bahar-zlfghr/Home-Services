package ir.maktab.web.specialist;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dtos.SpecialistDto;
import ir.maktab.exceptions.DuplicateEmailException;
import ir.maktab.exceptions.NotEmptyException;
import ir.maktab.service.service.ServiceService;
import ir.maktab.service.specialist.SpecialistService;
import ir.maktab.service.subservice.SubServiceService;
import ir.maktab.validationgroup.RegistrationGroup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    public SpecialistController(SpecialistService specialistService, ServiceService serviceService, SubServiceService subServiceService) {
        this.specialistService = specialistService;
        this.serviceService = serviceService;
        this.subServiceService = subServiceService;
    }

    @GetMapping("/registration")
    public ModelAndView registrationForm() {
        return new ModelAndView(
                "/specialist/registration",
                "specialistDto", new SpecialistDto());
    }

    @PostMapping("/registration")
    public String registerSpecialist(@ModelAttribute("specialistDto") @Validated(RegistrationGroup.class) SpecialistDto specialistDto,
                                     @RequestParam("picture") CommonsMultipartFile picture,
                                     Model model) throws DuplicateEmailException, NotEmptyException {
        specialistService.saveSpecialist(specialistDto, picture);
        model.addAttribute("success", true);
        model.addAttribute("specialistDto", new SpecialistDto());
        return "/specialist/registration";
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getModel());
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
}
