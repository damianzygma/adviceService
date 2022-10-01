package pl.com.mtd.adviceservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS = {
            "css/**",
            "js/**",
            "img/**",
            "scss/**",
            "vendor/**",
            "static/**"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("marian").password("{noop}marian12345").authorities("USER").and()
                .withUser("admin").password("{noop}admin").authorities("USER", "ADMIN").and()
                .withUser("mariola").password("{noop}1qaz@WSX").authorities("GUEST").and()
                .withUser("jaroslaw").password("{noop}donald123").authorities("USER").accountLocked(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login1")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/addQuestion")
                .authenticated()
                .anyRequest().permitAll();
    }
}
