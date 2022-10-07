package pl.com.mtd.adviceservice.converter;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.com.mtd.adviceservice.dto.PasswordDto;
import pl.com.mtd.adviceservice.model.User;
import pl.com.mtd.adviceservice.service.UserService;


@Component
public class PasswordConverter {

    private UserService userService;
    private PasswordEncoder passwordEncoder;


    public PasswordConverter(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
   public User convertUserPasswordDtoToEntity(PasswordDto passwordDto){
       User user = userService.getUserByNickname(userService.getLoggedUserName());
       String currentPassword =  passwordEncoder.encode(passwordDto.getCurrentPassword());
       if(!currentPassword.equals(user.getPassword())){
           if(passwordDto.getNewPassword().equals(passwordDto.getReenteredNewPassword())){
               user.setPassword(passwordEncoder.encode( passwordDto.getNewPassword()));

           }
       }
       return user;
   }

}
