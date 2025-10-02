package vn.iotstar.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/home")
    public String adminHome(HttpSession session, Model model) {
        Object user = session.getAttribute("user");
        model.addAttribute("user", user);
        return "admin/home"; // templates/admin/home.html
    }
}
