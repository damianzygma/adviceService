package pl.com.mtd.adviceservice.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import pl.com.mtd.adviceservice.converter.QuestionConverter;
import pl.com.mtd.adviceservice.dto.CategoryDto;
import pl.com.mtd.adviceservice.dto.QuestionDto;
import pl.com.mtd.adviceservice.model.Category;
import pl.com.mtd.adviceservice.model.Question;
import pl.com.mtd.adviceservice.model.User;
import pl.com.mtd.adviceservice.service.QuestionService;
import pl.com.mtd.adviceservice.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class QuestionsController {

    private final QuestionService questionService;
    private final QuestionConverter questionConverter;
    private final UserService userService;

    public QuestionsController(QuestionService questionService, QuestionConverter questionConverter, UserService userService) {
        this.questionService = questionService;
        this.questionConverter = questionConverter;
        this.userService = userService;
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

    @GetMapping("/addQuestion")
    public String getAddQuestion(Model model){
        QuestionDto questionDto = questionConverter.convertEntityToQuestionDto();
        model.addAttribute("question", questionDto);
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
