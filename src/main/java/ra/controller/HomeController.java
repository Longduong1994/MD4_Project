package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.dto.request.FormLoginDto;
import ra.dto.request.FormRegisterDto;
import ra.model.User;
import ra.service.impl.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "admin/index";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login", "login_form", new FormLoginDto());
    }

    @PostMapping("/handle-login")
    public String handleLogin(HttpSession session, @ModelAttribute("login_form") FormLoginDto formLoginDto, BindingResult bindingResult) {
        User user = userService.login(formLoginDto);
        if (user == null) {
            return "redirect:/login";
        } else if (user.getRole() == 1) {
            return "redirect:/admin";
        } else {
            session.setAttribute("userlogin", user);
            return "index";
        }
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession session) {
        session.removeAttribute("userlogin");
        return "redirect:/";
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register", "register_form", new FormRegisterDto());
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("register_form") FormRegisterDto formRegisterDto) {
        userService.save(formRegisterDto);
        return "redirect:/login";
    }

    @GetMapping("/shop")
    public ModelAndView shop() {
        return new ModelAndView("shop");
    }

    @GetMapping("/shopping-cart")
    public ModelAndView shoppingCart() {
        return new ModelAndView("shopping-cart");
    }

    @GetMapping("/check-out")
    public ModelAndView checkout() {
        return new ModelAndView("check-out");
    }

    @GetMapping("/blog")
    public ModelAndView blog() {
        return new ModelAndView("blog");
    }

    @GetMapping("/blog-details")
    public ModelAndView blogDetails() {
        return new ModelAndView("blog-details");
    }

    @GetMapping("/contact")
    public ModelAndView contact() {
        return new ModelAndView("contact");
    }

    @GetMapping("/shop-product")
    public ModelAndView product() {
        return new ModelAndView("product-detail");
    }


}
