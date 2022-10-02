package pl.com.mtd.adviceservice.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.com.mtd.adviceservice.model.User;

import java.util.Arrays;
import java.util.Collection;

public class ASUserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private User user;
    private String email;
    private String password;



    public ASUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();

    }

    public ASUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
