package pl.com.mtd.adviceservice.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.com.mtd.adviceservice.converter.PasswordConverter;
import pl.com.mtd.adviceservice.converter.UserConverter;
import pl.com.mtd.adviceservice.converter.UserProfileConverter;
import pl.com.mtd.adviceservice.dto.PasswordDto;
import pl.com.mtd.adviceservice.dto.UserDto;
import pl.com.mtd.adviceservice.dto.UserProfileDto;
import pl.com.mtd.adviceservice.model.DefaultUserDetails;
import pl.com.mtd.adviceservice.model.User;
import pl.com.mtd.adviceservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;


    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public void addUser(UserDto userDto){
        if(!isUserExist(userDto.getEmail())){
        User user = userConverter.convertUserDtoToEntity(userDto);
        user.setAuthority("USER");
        userRepository.save(user);
        System.out.println("adding a new user: " + user.getId());}
    }

    public void editUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public boolean isUserExist(String email){
        Optional<User> userFromDatabase = getAllUsers()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
        return userFromDatabase.isPresent();
    }

    public User getUserByNickname(String loggedUserName) {
        return userRepository.findUserByNickname(loggedUserName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByNickname = userRepository.findUserByNickname(username);
        DefaultUserDetails defaultUserDetails = new DefaultUserDetails(userByNickname.getPassword(), userByNickname.getNickname(), userByNickname.getAuthority());
        return defaultUserDetails;
    }

    public String getLoggedUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            return  ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }

}
