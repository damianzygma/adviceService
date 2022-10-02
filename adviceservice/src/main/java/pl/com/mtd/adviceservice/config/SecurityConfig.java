package pl.com.mtd.adviceservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.com.mtd.adviceservice.service.ASUserDetailsService;

import javax.sql.DataSource;

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

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("marian").password("{noop}marian12345").authorities("USER").and()
                .withUser("admin").password("{noop}admin").authorities("USER", "ADMIN").and()
                .withUser("mariola").password("{noop}1qaz@WSX").authorities("GUEST").and()
                .withUser("user").password("{noop}password").authorities("USER").accountLocked(true);

//        auth.jdbcAuthentication().dataSource(dataSource).authoritiesByUsernameQuery("SELECT email,password,enabled from Users where username = ?")
//                .groupAuthoritiesByUsername("SELECT email,authority from Authorities where username = ?")
//                .passwordEncoder(new BCryptPasswordEncoder());

//        auth.userDetailsService(new ASUserDetailsService());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login1")
                .and()
                .authorizeRequests()
                .antMatchers()
                .authenticated()
                .antMatchers(HttpMethod.GET,"/addQuestion")
                .authenticated()
                .anyRequest().permitAll();
    }


}
