package pl.com.mtd.adviceservice.service;

import org.springframework.stereotype.Service;
import pl.com.mtd.adviceservice.converter.UserAddConverter;
import pl.com.mtd.adviceservice.dto.UserAddDto;
import pl.com.mtd.adviceservice.model.User;
import pl.com.mtd.adviceservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserAddConverter userAddConverter;

    public UserService(UserRepository userRepository, UserAddConverter userAddConverter) {
        this.userRepository = userRepository;
        this.userAddConverter = userAddConverter;
    }

    public void addUser(UserAddDto userAddDto){
        User user = userAddConverter.userAddDtoToEntity(userAddDto);
        userRepository.save(user);
        System.out.println("adding a new user: " + user.getId());
    }

    public void editUser(UserAddDto newUserAddDto){
        User newUser = userAddConverter.userAddDtoToEntity(newUserAddDto);
        userRepository.save(newUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
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

    public Optional<User> getUserByNickname(String loggedUserName) {
        return Optional.ofNullable(userRepository.getUserByNickname(loggedUserName));
    }
}
