package pl.com.mtd.adviceservice.converter;

import org.springframework.stereotype.Component;
import pl.com.mtd.adviceservice.dto.UserProfileDto;
import pl.com.mtd.adviceservice.model.User;
import pl.com.mtd.adviceservice.service.UserService;

@Component
public class UserProfileConverter {

    private UserService userService;

    public UserProfileConverter(UserService userService) {
        this.userService = userService;
    }

    public User userProfileDtoToEntity(UserProfileDto userProfileDto) {
        User user = userService.getUserByNickname(userService.getLoggedUserName());
        user.setFirstName(userProfileDto.getName());
        user.setLastName(userProfileDto.getSurname());
        user.setNickname(userProfileDto.getNickname());
        user.setEmail(userProfileDto.getEmail());
        return user;
    }

    public UserProfileDto convertUserEntityToUserProfileDto(User userById){
        UserProfileDto dto = new UserProfileDto();
        dto.setName(userById.getFirstName());
        dto.setSurname(userById.getLastName());
        dto.setNickname(userById.getNickname());
        dto.setEmail(userById.getEmail());
        return dto;
    }

}
