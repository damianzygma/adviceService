package pl.com.mtd.adviceservice.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import pl.com.mtd.adviceservice.dto.QuestionDto;
import pl.com.mtd.adviceservice.model.Question;
import pl.com.mtd.adviceservice.model.User;
import pl.com.mtd.adviceservice.service.QuestionService;
import pl.com.mtd.adviceservice.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class QuestionsController {

    private final QuestionService questionService;
    private final UserService userService;

    public QuestionsController(QuestionService questionService, UserService userProfileService) {
        this.questionService = questionService;
        this.userService = userProfileService;
    }

    @GetMapping("/questions")
    public String getAllQuestionsFromCategory(Model model){
        List<Question> questions = questionService.getQuestions();
        model.addAttribute("questions", questions);
        return "category";
    }

//    tylko dla sprawdzenia działania widoku
    @GetMapping("/singleQuestion")
    public String getSingleQuestionView(){
        return "single-post";
    }

    @GetMapping("/addQuestion")
    public String getAddQuestion(Model model){
        String loggedUserName = getLoggedUserName();
        Optional<User> user = userService.getUserByNickname(loggedUserName);
        model.addAttribute("user", user);
        model.addAttribute("question", new QuestionDto());
        return "add-question";
    }

    private String getLoggedUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            return  ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @PostMapping("/addQuestion")
    public RedirectView saveQuestion(@ModelAttribute QuestionDto questionDto){
        questionService.addQuestion(questionDto);
        return new RedirectView("/questions");
    }

    @GetMapping("/editQuestion/{id}")
    public String getQuestion(@PathVariable("id") Long id, Model model){
//        Optional<User> user = userService.getUserById(id);
        Question question = questionService.getQuestion(id);
//        model.addAttribute("user", user);
        model.addAttribute("question", question);
        return "edit-question";
    }

    @PostMapping("/editQuestionSave/{id}")
    public RedirectView saveEditQuestion(@ModelAttribute Question newQuestion, @PathVariable("id") Long id){
        questionService.editQuestion(newQuestion);
        return new RedirectView("/questions");
    }

//    wyłącznie dla sprawdzenia widoku
    @GetMapping("editQuestion")
    public String getEditQuestionView(){
        return "edit-question";
    }

    public RedirectView deleteQuestion(@PathVariable("id") Long id){
        questionService.deleteQuestion(id);
        return new RedirectView("/questions");
    }





}
