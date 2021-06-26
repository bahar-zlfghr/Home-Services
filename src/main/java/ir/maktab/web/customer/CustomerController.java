package ir.maktab.web.customer;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.data.enums.PersonRole;
import ir.maktab.data.enums.UserStatus;
import ir.maktab.dtos.*;
import ir.maktab.dtos.factory.AccountFactory;
import ir.maktab.exceptions.DuplicateEmailException;
import ir.maktab.exceptions.NotFoundUserException;
import ir.maktab.service.customer.CustomerService;
import ir.maktab.validationgroup.LoginGroup;
import ir.maktab.validationgroup.RegistrationGroup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Bahar Zolfaghari
 **/
@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/registration")
    public ModelAndView registrationForm() {
        return new ModelAndView(
                "/customer/registration",
                "customerDto", new CustomerDto());
    }

    @PostMapping("/registration")
    public String registerCustomer(@ModelAttribute("customerDto") @Validated(RegistrationGroup.class) CustomerDto customerDto,
                                   Model model) throws DuplicateEmailException {
        customerService.checkDuplicateEmail(customerDto.getEmail());
        AccountDto accountDto = AccountFactory.createAccount();
        customerDto.setAccountDto(accountDto);
        customerDto.setRole(PersonRole.CUSTOMER);
        customerDto.setStatus(UserStatus.NEW);
        customerService.saveCustomer(customerDto);
        model.addAttribute("customerDto", customerDto);
        return "/customer/registerSuccess";
    }

    @GetMapping("/login")
    public ModelAndView loginForm() {
        return new ModelAndView(
                "/customer/login",
                "customerDto", new CustomerDto());
    }

    @PostMapping("/login")
    public String loginCustomer(@ModelAttribute("customerDto") @Validated(LoginGroup.class) CustomerDto customerDto,
                                Model model) throws NotFoundUserException {
        CustomerDto customerByEmailAndPassword = customerService.getCustomerByEmailAndPassword(customerDto.getEmail(), customerDto.getPassword());
        model.addAttribute("customerDto", customerByEmailAndPassword);
        return "/customer/panel";
    }

    @GetMapping("/logout")
    public ModelAndView logoutButton() {
        ModelAndView modelAndView = new ModelAndView(
                "/customer/login",
                "customerDto", new CustomerDto());
        modelAndView.addObject("logout", true);
        return modelAndView;
    }

    @GetMapping("/panel")
    public String customerPanel() {
        return "/customer/panel";
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
        model.put("customerDto", new CustomerDto());
        return new ModelAndView(lastView, model);
    }

    @ExceptionHandler(value = NotFoundUserException.class)
    public ModelAndView notFoundUserExceptionHandler(NotFoundUserException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        model.put("errorLogin", ex.getMessage());
        model.put("customerDto", new CustomerDto());
        return new ModelAndView(lastView, model);
    }
}
