package pl.com.mtd.adviceservice.converter;

import org.springframework.stereotype.Component;
import pl.com.mtd.adviceservice.dto.PasswordDto;
import pl.com.mtd.adviceservice.model.User;
import pl.com.mtd.adviceservice.service.UserService;


@Component
public class PasswordConverter {

    private UserService userService;

    public PasswordConverter(UserService userService) {
        this.userService = userService;
    }
   public User convertUserPasswordDtoToEntity(PasswordDto passwordDto){
       User user = userService.getUserByNickname(userService.getLoggedUserName());
       String currentPassword =  passwordDto.getCurrentPassword();
       if(currentPassword.equals(user.getPassword())){
           if(passwordDto.getNewPassword().equals(passwordDto.getReenteredNewPassword())){
               user.setPassword( passwordDto.getNewPassword());
           }
       }
       return user;
   }

}
