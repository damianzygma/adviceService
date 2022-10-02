package pl.com.mtd.adviceservice.converter;

import org.springframework.stereotype.Component;
import pl.com.mtd.adviceservice.dto.UserAddDto;
import pl.com.mtd.adviceservice.model.User;

@Component
public class UserAddConverter {

    public User userAddDtoToEntity(UserAddDto userAddDto) {
        User user = new User();
        user.setEmail(userAddDto.getEmail());
        user.setPassword(userAddDto.getPassword());
        user.setFirstName(userAddDto.getName());
        user.setLastName(userAddDto.getSurname());
        user.setNickname(userAddDto.getNickname());
        user.setQuestionForPassword(userAddDto.getQuestionForPassword());
        user.setQuestionAnswer(userAddDto.getAnswerForQuestion());
        return user;
    }

    public UserAddDto convertUserEntityToUserAddDto(User user){
        UserAddDto dto = new UserAddDto();
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setName(user.getFirstName());
        dto.setSurname(user.getLastName());
        dto.setNickname(user.getNickname());
        dto.setQuestionForPassword(user.getQuestionForPassword());
        dto.setAnswerForQuestion(user.getQuestionAnswer());
        return dto;
    }
}
