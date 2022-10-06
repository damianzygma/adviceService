package pl.com.mtd.adviceservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import pl.com.mtd.adviceservice.converter.PasswordConverter;
import pl.com.mtd.adviceservice.converter.UserProfileConverter;
import pl.com.mtd.adviceservice.dto.PasswordDto;
import pl.com.mtd.adviceservice.dto.UserDto;
import pl.com.mtd.adviceservice.dto.UserProfileDto;
import pl.com.mtd.adviceservice.model.User;
import pl.com.mtd.adviceservice.service.UserService;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    private UserProfileConverter userProfileConverter;

    private PasswordConverter passwordConverter;

    public UserController(UserService userService, UserProfileConverter userProfileConverter, PasswordConverter passwordConverter) {
        this.userService = userService;
        this.userProfileConverter = userProfileConverter;
        this.passwordConverter = passwordConverter;
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        List<String> helperQuestion = Arrays.asList("What is your mother's maiden name?",
                "What is the name of your first pet?",
                "What was your first car?",
                "What elementary school did you attend?",
                "What is the name of the town where you were born?");
        model.addAttribute("QuestionList", helperQuestion);
        model.addAttribute("user", new UserDto());

        return "add-user";
    }

    @PostMapping("/register")
    public RedirectView processRegistration(@ModelAttribute("user") UserDto userdto) {
        userService.addUser(userdto);
        return new RedirectView("/login1");
    }

    @GetMapping("/user/{nickname}")
    public String getUserProfileView(@PathVariable("nickname") String nickname, Model model) {
        User user = userService.getUserByNickname(nickname);
        UserProfileDto userProfileDto = userProfileConverter.convertUserEntityToUserProfileDto(user);
        model.addAttribute("userProfile", userProfileDto);
        return "user";
    }

    @PostMapping("/user/{nickname}")
    public RedirectView editUserProfile(@PathVariable("nickname") String nickname, @ModelAttribute UserProfileDto userProfileDto){
        User user = userProfileConverter.userProfileDtoToEntity(userProfileDto);
        userService.editUser(user);
        return new RedirectView("/");
    }

    @GetMapping("/user/password")
    public String getViewForChangingPassword(Model model){
        model.addAttribute("password", new PasswordDto());
        return "change-password";
    }

    @PostMapping("/user/password")
    public RedirectView changePassword(@ModelAttribute PasswordDto passwordDto){
        User user = passwordConverter.convertUserPasswordDtoToEntity(passwordDto);
        userService.editUser(user);
        return new RedirectView("/");
    }

    @GetMapping("/login1")
    public String getLoginView() {
        return "login";
    }

    @PostMapping("/deleteLogout")
    public String deleteProfile(){
        Long id = userService.getUserByNickname(userService.getLoggedUserName()).getId();
        userService.deleteUser(id);

        return "index";
    }
}
