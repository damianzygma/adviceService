package pl.com.mtd.adviceservice.converter;

import org.springframework.stereotype.Component;
import pl.com.mtd.adviceservice.dto.UserDto;
import pl.com.mtd.adviceservice.model.User;

@Component
public class UserConverter {

    public User convertUserDtoToEntity(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getName());
        user.setLastName(userDto.getSurname());
        user.setNickname(userDto.getNickname());
        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
        user.setQuestionForPassword(userDto.getQuestionForPassword());
        user.setQuestionAnswer(userDto.getAnswerForQuestion());
        return user;
    }

}
