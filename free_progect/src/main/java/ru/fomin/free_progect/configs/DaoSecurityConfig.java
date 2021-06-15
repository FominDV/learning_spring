package ru.fomin.free_progect.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.fomin.free_progect.services.UserService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class DaoSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/cart/**").authenticated()
                .antMatchers("/moderator/**").hasRole("MODERATOR")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/product", true)
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .usernameParameter("email")
                .and()
                .logout()
                .logoutSuccessUrl("/product");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}