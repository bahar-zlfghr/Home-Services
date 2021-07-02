package ir.maktab.web.account;

import ir.maktab.dtos.ConfirmationTokenDto;
import ir.maktab.dtos.UserDto;
import ir.maktab.exceptions.NotFoundConfirmationTokenException;
import ir.maktab.exceptions.NotFoundUserException;
import ir.maktab.service.token.ConfirmationTokenService;
import ir.maktab.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Bahar Zolfaghari
 **/
@Controller
@RequestMapping("/")
public class UserAccountController {
    private final ConfirmationTokenService confirmationTokenService;
    private final UserService userService;

    public UserAccountController(ConfirmationTokenService confirmationTokenService, UserService userService) {
        this.confirmationTokenService = confirmationTokenService;
        this.userService = userService;
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@RequestParam("token") String token,
                                     Model model) throws NotFoundConfirmationTokenException, NotFoundUserException {
        model.addAttribute("hasError", false);
        ConfirmationTokenDto confirmationTokenDto = confirmationTokenService.findConfirmationTokenByTokenAndEnabled(token, true);
        UserDto userDto = userService.findUserByConfirmToken(confirmationTokenDto);
        confirmationTokenService.updateConfirmationTokenEnabled(confirmationTokenDto, false);
        userService.updateUserStatus(userDto);
        return "/accountVerified";
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        return new ModelAndView("/accountVerified", ex.getModel());
    }

    @ExceptionHandler(value = NotFoundConfirmationTokenException.class)
    public ModelAndView notFoundConfirmationTokenExceptionHandler(NotFoundConfirmationTokenException ex, HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("hasError", true);
        model.put("tokenNotFound", ex.getMessage());
        return new ModelAndView("/accountVerified", model);
    }
}
