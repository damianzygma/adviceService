package pl.com.mtd.adviceservice.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "category")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "category")
    private List<Subcategory> subcategories = new ArrayList<>();

    public Category() {
    }

    public Category(Long id, String name, List<Question> questions, List<Subcategory> subcategories) {
        this.id = id;
        this.name = name;
        this.questions = questions;
        this.subcategories = subcategories;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}
