package com.example.TeleMediaX.config;

import com.example.TeleMediaX.model.AuthenticationRequest;
import com.example.TeleMediaX.repo.UserRepo;
import com.example.TeleMediaX.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private UserService userService;
    private User user;
    //@Autowired
    // public AuthenticationRequest authenticationRequest;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated();
        http.authenticationProvider(authenticationProvider());
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        //return new BCryptPasswordEncoder(10);
       return new PasswordEncoder() {
            final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10, new SecureRandom());
            @Override
            public String encode(CharSequence rawPassword) {
                //rawPassword = authenticationRequest.getPassword();
               // rawPassword = user.getPassword();
                System.out.println("raw password::"+rawPassword);
                return BCrypt.hashpw(rawPassword.toString(),BCrypt.gensalt(8));

            }

           /* @Bean
            public AuthenticationRequest authenticationRequest(){
                return new AuthenticationRequest();
           }*/

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                //System.out.println("Encoded Password::"+ encodedPassword);
               return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
            }
        };
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepo userRepo) {
        return args -> {
        AuthenticationRequest User1 = userRepo.save(new AuthenticationRequest("john", "12345"));

            AuthenticationRequest User2 = userRepo.save(new AuthenticationRequest("Smith", "12345"));
        };
    }
}
