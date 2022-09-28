package pl.com.mtd.adviceservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.com.mtd.adviceservice.model.User;
import pl.com.mtd.adviceservice.repository.UserRepository;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public String getUserView(){
        return "user";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/user")
    public String processRegistration(User user){
        userRepository.save(user);
        return "user";
    }

    @GetMapping("/login1")
    public String getLoginView(){
        return "login";
    }
}
