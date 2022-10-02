package pl.com.mtd.adviceservice.model;

import org.springframework.security.core.GrantedAuthority;

public class DefaultGrantedAuthority implements GrantedAuthority {

    private String authority;

    public DefaultGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
