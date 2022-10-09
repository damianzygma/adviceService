package pl.com.mtd.adviceservice.converter;

import org.springframework.stereotype.Component;
import pl.com.mtd.adviceservice.dto.UserProfileDto;
import pl.com.mtd.adviceservice.model.User;

@Component
public class UserProfileConverter {

    public UserProfileDto convertUserEntityToUserProfileDto(User userById) {
        UserProfileDto dto = new UserProfileDto();
        dto.setName(userById.getFirstName());
        dto.setSurname(userById.getLastName());
        dto.setNickname(userById.getNickname());
        dto.setEmail(userById.getEmail());
        return dto;
    }

}
