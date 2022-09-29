package pl.com.mtd.adviceservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import pl.com.mtd.adviceservice.model.Question;
import pl.com.mtd.adviceservice.model.User;
import pl.com.mtd.adviceservice.service.QuestionService;
import pl.com.mtd.adviceservice.service.UserService;

import java.util.List;

@Controller
public class QuestionsController {


    private final UserService userService;

    private final QuestionService questionService;

    @Autowired
    public QuestionsController(UserService userService, QuestionService questionService) {
        this.userService = userService;
        this.questionService = questionService;
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
        List<User> users = userService.getUsers();
        model.addAttribute("user", users);
        return "add-question";
    }

    @PostMapping("/addNewQuestion")
    public RedirectView saveQuestion(@ModelAttribute Question newQuestion){
        questionService.addQuestion(newQuestion);
        return new RedirectView("/questions");
    }

    @GetMapping("/editQuestion/{id}")
    public String getQuestion(@PathVariable("id") Long id, Model model){
        List<User> list = userService.getUsers();
        Question question = questionService.getQuestion(id);
        model.addAttribute("user", list);
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
