package pl.com.mtd.adviceservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.com.mtd.adviceservice.model.User;
import pl.com.mtd.adviceservice.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

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
        List<String>helperQuestion = Arrays.asList("What is your mother's maiden name?",
                "What is the name of your first pet?",
                "What was your first car?",
                "What elementary school did you attend?",
                "What is the name of the town where you were born?");
        model.addAttribute("QuestionList", helperQuestion);
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/add-user")
    public String processRegistration(User user){
        userRepository.save(user);
        return "user";
    }

    @GetMapping("/login1")
    public String getLoginView(){
        return "login";
    }
}
