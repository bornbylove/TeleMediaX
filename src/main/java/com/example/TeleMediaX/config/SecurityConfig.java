package com.example.TeleMediaX.config;

import com.example.TeleMediaX.model.AuthenticationRequest;
import com.example.TeleMediaX.repo.UserRepo;
import com.example.TeleMediaX.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
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
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
    UserRepo userRepo(){
        return new UserRepo() {
            @Override
            public AuthenticationRequest findByUserName(String userName) {
                return new AuthenticationRequest();
            }

            @Override
            public List<AuthenticationRequest> findAll() {
                return null;
            }

            @Override
            public List<AuthenticationRequest> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<AuthenticationRequest> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public <S extends AuthenticationRequest> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends AuthenticationRequest> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends AuthenticationRequest> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<AuthenticationRequest> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public AuthenticationRequest getOne(Long aLong) {
                return null;
            }

            @Override
            public AuthenticationRequest getById(Long aLong) {
                return null;
            }

            @Override
            public AuthenticationRequest getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends AuthenticationRequest> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends AuthenticationRequest> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<AuthenticationRequest> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends AuthenticationRequest> S save(S entity) {
                return null;
            }

            @Override
            public Optional<AuthenticationRequest> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(AuthenticationRequest entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends AuthenticationRequest> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends AuthenticationRequest> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends AuthenticationRequest> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends AuthenticationRequest> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends AuthenticationRequest> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends AuthenticationRequest, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
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
