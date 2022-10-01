package pl.com.mtd.adviceservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(length = 64)
    private String questionForPassword;

    @Column(length = 45)
    private String questionAnswer;
    @Column(nullable = false, length = 20)
    private String firstName;
    @Column(nullable = false, length = 20)
    private String lastName;

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "user")
    private List<Question> questions;

    public User() {
    }

    public User(Long id, String email, String password, String questionForPassword, String questionAnswer,
                String firstName, String lastName, List<Question> questions) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.questionForPassword = questionForPassword;
        this.questionAnswer = questionAnswer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getQuestionForPassword() {
        return questionForPassword;
    }

    public void setQuestionForPassword(String questionForPassword) {
        this.questionForPassword = questionForPassword;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}