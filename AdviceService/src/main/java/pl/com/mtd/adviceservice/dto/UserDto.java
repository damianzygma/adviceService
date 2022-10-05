package pl.com.mtd.adviceservice.dto;

import org.springframework.stereotype.Component;

@Component
public class UserDto {

    private String name;
    private String surname;
    private String nickname;
    private String email;
    private String password;
    private String questionForPassword;
    private String answerForQuestion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuestionForPassword() {
        return questionForPassword;
    }

    public void setQuestionForPassword(String questionForPassword) {
        this.questionForPassword = questionForPassword;
    }

    public String getAnswerForQuestion() {
        return answerForQuestion;
    }

    public void setAnswerForQuestion(String answerForQuestion) {
        this.answerForQuestion = answerForQuestion;
    }
}
