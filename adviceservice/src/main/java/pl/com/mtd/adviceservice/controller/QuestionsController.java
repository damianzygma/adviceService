package pl.com.mtd.adviceservice.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.com.mtd.adviceservice.converter.QuestionConverter;
import pl.com.mtd.adviceservice.converter.validators.QuestionValidator;
import pl.com.mtd.adviceservice.dto.CategoryDto;
import pl.com.mtd.adviceservice.dto.QuestionDto;
import pl.com.mtd.adviceservice.model.Category;
import pl.com.mtd.adviceservice.model.Question;
import pl.com.mtd.adviceservice.model.User;
import pl.com.mtd.adviceservice.service.CategoryService;
import pl.com.mtd.adviceservice.service.QuestionService;
import pl.com.mtd.adviceservice.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class QuestionsController {

    private final QuestionService questionService;
    private final UserService userService;
    private final QuestionValidator questionValidator;
    private final CategoryService categoryService;

    @ModelAttribute("loggedUser")
    private User loggedUser(){
        return userService.getLoggedUserName();
    }

    public QuestionsController(QuestionService questionService, UserService userService, QuestionValidator questionValidator,
                               CategoryService categoryService) {
        this.questionService = questionService;
        this.userService = userService;
        this.questionValidator = questionValidator;
        this.categoryService = categoryService;
    }

    @GetMapping("/questions")
    public String getAllQuestionsFromCategory(){
//        List<Question> questions = questionService.getQuestions();
//        model.addAttribute("questions", questions);
        return "category";
    }

//    tylko dla sprawdzenia działania widoku
    @GetMapping("/singleQuestion")
    public String getSingleQuestionView(){
        return "single-post";
    }

    @InitBinder("questionDto")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(questionValidator);
    }
    @GetMapping("/addQuestion")
    public String getAddQuestion(Model model){
        QuestionDto questionDto = new QuestionDto();
        model.addAttribute("question", questionDto);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "add-question";
    }

    @PostMapping("/addQuestion")
    public String saveQuestion(@Valid @ModelAttribute QuestionDto questionDto, BindingResult result){
        if(result.hasErrors()){
            return "add-question";
        }
        questionService.addQuestion(questionDto);
        return "redirect:/questions";
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
