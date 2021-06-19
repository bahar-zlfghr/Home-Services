package ir.maktab.web.user;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.data.enums.PersonRole;
import ir.maktab.dtos.*;
import ir.maktab.dtos.factory.AccountFactory;
import ir.maktab.dtos.factory.ProfilePictureFactory;
import ir.maktab.dtos.factory.UserDtoFactory;
import ir.maktab.exceptions.DuplicateEmailException;
import ir.maktab.exceptions.NotEmptyProfilePictureException;
import ir.maktab.exceptions.NotEmptySpecialtyException;
import ir.maktab.service.customer.CustomerService;
import ir.maktab.service.specialist.SpecialistService;
import ir.maktab.validationgroup.RegistrationGroup;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/user")
public class UserController {
    private final CustomerService customerService;
    private final SpecialistService specialistService;

    public UserController(CustomerService customerService, SpecialistService specialistService) {
        this.customerService = customerService;
        this.specialistService = specialistService;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Secured({"ROLE_MANAGER"})
    @GetMapping("/registration")
    public ModelAndView registrationUserForm() {
        return new ModelAndView(
                "/register/registration",
                "userDto", new UserDto()
        );
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Secured({"ROLE_MANAGER"})
    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("userDto") @Validated(RegistrationGroup.class) UserDto userDto,
                               @RequestParam("specialty") @Validated(RegistrationGroup.class) String specialty,
                               @RequestParam("profilePicture") CommonsMultipartFile profilePicture,
                               Model model) throws DuplicateEmailException, NotEmptySpecialtyException, NotEmptyProfilePictureException {
        if (userDto.getRole().equals(PersonRole.CUSTOMER.getRole())) {
            customerService.checkDuplicateEmail(userDto.getEmail());

            CustomerDto customerDto = (CustomerDto) UserDtoFactory.createUser(userDto, PersonRole.CUSTOMER);

            AccountDto accountDto = AccountFactory.createAccount();
            customerDto.setAccountDto(accountDto);

            customerService.saveCustomer(customerDto);

            model.addAttribute("role", "Customer");
            model.addAttribute("user", customerDto);
        }
        else if (userDto.getRole().equals("specialist")) {
            specialistService.checkDuplicateEmail(userDto.getEmail());

            SpecialistDto specialistDto = (SpecialistDto) UserDtoFactory.createUser(userDto, PersonRole.SPECIALIST);
            specialistDto
                    .setSpecialty(specialty)
                    .setScore(0);
            ProfilePictureDto profilePictureDto = ProfilePictureFactory.createProfilePicture(profilePicture, specialistDto);
            specialistDto.setProfilePictureDto(profilePictureDto);

            AccountDto accountDto = AccountFactory.createAccount();
            specialistDto.setAccountDto(accountDto);

            specialistService.saveSpecialist(specialistDto);
            model.addAttribute("role", "Specialist");
            model.addAttribute("user", specialistDto);
        }
        return "/register/userSaveSuccess";
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getModel());
    }

    @ExceptionHandler(value = DuplicateEmailException.class)
    public ModelAndView duplicateErrorHandler(DuplicateEmailException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);

        Map<String, Object> model = new HashMap<>();
        model.put("duplicateEmailError", ex.getMessage());
        model.put("userDto", new UserDto());

        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = NotEmptySpecialtyException.class)
    public ModelAndView notEmptySpecialtyErrorHandler(NotEmptySpecialtyException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);

        Map<String, Object> model = new HashMap<>();
        model.put("notEmptySpecialtyError", ex.getMessage());
        model.put("userDto", new UserDto());

        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = NotEmptyProfilePictureException.class)
    public ModelAndView notEmptyProfilePictureErrorHandler(NotEmptyProfilePictureException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);

        Map<String, Object> model = new HashMap<>();
        model.put("notEmptyProfilePictureError", ex.getMessage());
        model.put("userDto", new UserDto());

        return new ModelAndView(lastView, model);
    }
}
