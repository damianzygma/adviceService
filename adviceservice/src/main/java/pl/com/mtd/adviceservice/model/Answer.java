package pl.com.mtd.adviceservice.model;


import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 3000)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date answerDate;

    private Integer Rating;

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "answer")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer() {
    }

    public Answer(Long id, User user, String description, Date answerDate, Integer rating, Set<Comment> comments,
                  Question question) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.answerDate = answerDate;
        Rating = rating;
        this.comments = comments;
        this.question = question;
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

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public Integer getRating() {
        return Rating;
    }

    public void setRating(Integer rating) {
        Rating = rating;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
