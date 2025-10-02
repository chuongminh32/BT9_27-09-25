package vn.iotstar.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import vn.iotstar.entity.User;
import vn.iotstar.service.UserService;

@Controller
public class LoginController {

    private final UserService userService;
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", "Sai tài khoản hoặc không có quyền");
        }
        return "login"; // templates/login.html
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession session) {
        User u = userService.login(email, password);
        if (u == null) return "redirect:/login?error=1";

        session.setAttribute("user", u);
        session.setAttribute("role", u.getRole());

        if ("ADMIN".equalsIgnoreCase(u.getRole())) {
            return "redirect:/admin/home";
        } else {
            return "redirect:/user/home";
        }
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // templates/register.html
    }

    @PostMapping("/register")
    public String doRegister(@Valid @ModelAttribute("user") User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        user.setRole("USER"); // mặc định USER
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
