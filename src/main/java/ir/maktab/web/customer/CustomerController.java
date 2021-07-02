package ir.maktab.web.customer;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.dtos.*;
import ir.maktab.dtos.creator.AddressCreator;
import ir.maktab.dtos.creator.CustomerCreator;
import ir.maktab.dtos.creator.MailCreator;
import ir.maktab.dtos.creator.OrderCreator;
import ir.maktab.exceptions.DuplicateEmailException;
import ir.maktab.exceptions.InvalidSuggestedPriceException;
import ir.maktab.exceptions.NotFoundSubServiceException;
import ir.maktab.exceptions.NotFoundUserException;
import ir.maktab.service.customer.CustomerService;
import ir.maktab.service.mail.EmailSenderService;
import ir.maktab.service.order.OrderService;
import ir.maktab.service.service.ServiceService;
import ir.maktab.service.subservice.SubServiceService;
import ir.maktab.service.token.ConfirmationTokenService;
import ir.maktab.validationgroup.RegistrationGroup;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Bahar Zolfaghari
 **/
@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final SubServiceService subServiceService;
    private final ServiceService serviceService;
    private final OrderService orderService;
    private final EmailSenderService emailSenderService;
    private final ConfirmationTokenService confirmationTokenService;

    public CustomerController(CustomerService customerService, SubServiceService subServiceService, ServiceService serviceService,
                              OrderService orderService, EmailSenderService emailSenderService, ConfirmationTokenService confirmationTokenService) {
        this.customerService = customerService;
        this.subServiceService = subServiceService;
        this.serviceService = serviceService;
        this.orderService = orderService;
        this.emailSenderService = emailSenderService;
        this.confirmationTokenService = confirmationTokenService;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("")
    public String customer() {
        return "/customer/customer";
    }

    @GetMapping("/register")
    public ModelAndView registrationForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/registration");
        modelAndView.addObject("customerDto", new CustomerDto());
        modelAndView.addObject("successRegister", false);
        return modelAndView;
    }

    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute("customerDto") @Validated(RegistrationGroup.class) CustomerDto customerDto,
                                   Model model) throws DuplicateEmailException {
        CustomerDto customer = CustomerCreator.createCustomerDto(customerDto);
        customerService.saveCustomer(customer);
        emailSenderService.sendEmail(MailCreator.createEmail(customerDto, customer.getConfirmationTokenDto()));
        model.addAttribute("customerEmail", customerDto.getEmail());
        model.addAttribute("successRegister", true);
        model.addAttribute("customerDto", new CustomerDto());
        return "/customer/registration";
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/create-order")
    public ModelAndView createOrderForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/createOrder");
        modelAndView.addObject("successCreatOrder", false);
        modelAndView.addObject("subServices", subServiceService.getAllSubServices());
        return modelAndView;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/create-order")
    public String createOrder(@RequestParam("subServiceName") String subServiceName,
                              @RequestParam("suggestedPrice") String suggestedPrice,
                              @RequestParam("description") String description,
                              @RequestParam("doDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date doDate,
                              @RequestParam("state") String state,
                              @RequestParam("city") String city,
                              @RequestParam("formatted_address") String formattedAddress,
                              Principal principal,
                              Model model) throws NotFoundSubServiceException, NotFoundUserException, InvalidSuggestedPriceException {
        CustomerDto customerDto = customerService.getCustomerByEmail(principal.getName());
        SubServiceDto subServiceDto = subServiceService.getSubServiceByName(subServiceName);
        AddressDto addressDto = AddressCreator.createAddress(state, city, formattedAddress);
        OrderDto orderDto = OrderCreator.createOrder(customerDto, subServiceDto, suggestedPrice, description, doDate, addressDto);
        orderService.saveOrder(orderDto);
        model.addAttribute("successCreatOrder", true);
        model.addAttribute("subServices", subServiceService.getAllSubServices());
        model.addAttribute("customerDto", new CustomerDto());
        return "/customer/createOrder";
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

    @ExceptionHandler(value = InvalidSuggestedPriceException.class)
    public ModelAndView invalidSuggestedPriceExceptionHandler(InvalidSuggestedPriceException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        Map<String, Object> model = new HashMap<>();
        model.put("successCreatOrder", false);
        model.put("invalidSuggestedPrice", ex.getMessage());
        model.put("subServices", subServiceService.getAllSubServices());
        model.put("customerDto", new CustomerDto());
        return new ModelAndView(lastView, model);
    }
}
