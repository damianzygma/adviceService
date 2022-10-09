package pl.com.mtd.adviceservice.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
    private final UserProfileConverter userProfileConverter;

    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, UserConverter userConverter, UserProfileConverter userProfileConverter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.userProfileConverter = userProfileConverter;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(UserDto userDto) {
        if (!isUserExist(userDto.getEmail())) {
            User user = userConverter.convertUserDtoToEntity(userDto);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setAuthority("USER");
            userRepository.save(user);
            System.out.println("adding a new user: " + user.getId());
        }
    }

    public void editUser(UserProfileDto userProfileDto) {
        User user = getLoggedUserEntity();
        user.setFirstName(userProfileDto.getName());
        user.setLastName(userProfileDto.getSurname());
        user.setEmail(userProfileDto.getEmail());
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean isUserExist(String email) {
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

    public UserProfileDto getLoggedUser() {
        return userProfileConverter.convertUserEntityToUserProfileDto(getLoggedUserEntity());
    }

    public User getLoggedUserEntity() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return getUserByNickname(((UserDetails) principal).getUsername());
        } else {
            return getUserByNickname(principal.toString());
        }
    }

    public void changePassword(PasswordDto passwordDto) {
        User user = getLoggedUserEntity();
        String currentPassword = passwordEncoder.encode(passwordDto.getCurrentPassword());
        if (currentPassword.equals(user.getPassword())) {
            if (passwordDto.getNewPassword().equals(passwordDto.getReenteredNewPassword())) {
                user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
            }
        }
        userRepository.save(user);
    }

}
