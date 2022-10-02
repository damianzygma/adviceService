package pl.com.mtd.adviceservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import pl.com.mtd.adviceservice.config.ASUserDetails;
import pl.com.mtd.adviceservice.model.User;
import pl.com.mtd.adviceservice.repository.UserRepository;

@Service
public class ASUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findUserByNickname(username);
        return new ASUserDetails(user);

    }
}
