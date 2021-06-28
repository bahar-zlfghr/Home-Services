package ir.maktab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Bahar Zolfaghari
 **/
@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String manager() {
        return "/manager/manager";
    }

    @GetMapping("/specialist")
    public String specialist() {
        return "/specialist/specialist";
    }

    @GetMapping("/customer")
    public String customer() {
        return "/customer/customer";
    }
}
