package pl.com.mtd.adviceservice.model;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.*;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 3000)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date questionDate;

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "question")
    private Set<Category> category = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    public Question() {
    }

    public Question(Long id, User user, String description, Date questionDate, Set<Category> category, List<Answer> answers) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.questionDate = questionDate;
        this.category = category;
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
