package pl.com.mtd.adviceservice.model;

import javax.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Category() {
    }

    public Category(Long id, String name, Question question) {
        this.id = id;
        this.name = name;
        this.question = question;
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
