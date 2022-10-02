package pl.com.mtd.adviceservice.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String notSecurePassword = "admin";
        String encodedPassword = encoder.encode(notSecurePassword);
        System.out.println(encodedPassword);
    }
}
