package pl.com.mtd.adviceservice.converter;


import org.springframework.stereotype.Component;
import pl.com.mtd.adviceservice.dto.CredentialDto;
import pl.com.mtd.adviceservice.model.User;

@Component
public class UserLoginConverter {

    public User userLoginDtoToEntity(CredentialDto userLoginDto) {
        User user = new User();
        user.setId(userLoginDto.getId());
        user.setPassword(userLoginDto.getPassword());
        user.setNickname(userLoginDto.getNickname());
        return user;
    }

    public CredentialDto convertUserEntityToUserLoginDto(User user){
        CredentialDto dto = new CredentialDto();
        dto.setId(user.getId());
        dto.setPassword(user.getPassword());
        dto.setNickname(user.getNickname());
        return dto;
    }
}
