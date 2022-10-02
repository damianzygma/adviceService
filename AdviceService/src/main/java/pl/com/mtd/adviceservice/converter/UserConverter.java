package pl.com.mtd.adviceservice.converter;

import org.springframework.stereotype.Component;
import pl.com.mtd.adviceservice.dto.UserProfileDto;
import pl.com.mtd.adviceservice.model.User;

@Component
public class UserConverter {

    public User userProfileDtoToEntity(UserProfileDto userProfileDto) {
        User user = new User();
        user.setId(userProfileDto.getId());
        user.setEmail(userProfileDto.getEmail());
        user.setPassword(userProfileDto.getPassword());
        user.setFirstName(userProfileDto.getName());
        user.setLastName(userProfileDto.getSurname());
        user.setNickname(userProfileDto.getNickname());
        return user;
    }

    public UserProfileDto convertUserEntityToUserProfileDto(User user){
        UserProfileDto dto = new UserProfileDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setName(user.getFirstName());
        dto.setSurname(user.getLastName());
        dto.setNickname(user.getNickname());
        return dto;
    }

}
