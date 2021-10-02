package ir.maktab.web;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dtos.PersonDto;
import ir.maktab.exceptions.NotFoundUserException;
import ir.maktab.exceptions.PasswordNotMatchedException;
import ir.maktab.service.person.PersonService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author : Bahar Zolfaghari
 **/
@Controller
public class MainController {
    private final PersonService personService;

    public MainController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        if (!Objects.isNull(principal)) {
            PersonDto personDto = personService.findPersonByEmail(principal.getName());
            model.addAttribute("message", "Welcome, You are logged in as " + principal.getName());
            switch (personDto.getRole()) {
                case MANAGER:
                    return "/manager/managerHome";
                case SPECIALIST:
                    return "specialist/specialistHome";
                case CUSTOMER:
                    return "customer/customerHome";
            }
        }
        return "/home";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'SPECIALIST', 'CUSTOMER')")
    @GetMapping("/change-password")
    public ModelAndView changePasswordForm(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("/changePassword");
        modelAndView.addObject("successChange", false);
        modelAndView.addObject("email", principal.getName());
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'SPECIALIST', 'CUSTOMER')")
    @PostMapping("/change-password")
    public String changePassword(@RequestParam("email") String email,
                                       @RequestParam("previousPassword") String previousPassword,
                                       @RequestParam("newPassword") String newPassword,
                                       Principal principal,
                                       Model model) throws PasswordNotMatchedException, NotFoundUserException {
        personService.updatePersonPassword(email, previousPassword, newPassword);
        model.addAttribute("successChange", true);
        model.addAttribute("email", principal.getName());
        return "/changePassword";
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getModel());
    }

    @ExceptionHandler(value = PasswordNotMatchedException.class)
    public ModelAndView passwordNotMatchedExceptionHandler(PasswordNotMatchedException ex, HttpServletRequest request, Principal principal) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        model.put("passwordNotMatched", ex.getMessage());
        model.put("successChange", false);
        model.put("email", principal.getName());
        return new ModelAndView(lastView, model);
    }
}
