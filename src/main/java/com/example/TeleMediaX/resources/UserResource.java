package com.example.TeleMediaX.resources;

import com.example.TeleMediaX.Exception.UserNotFoundException;
import com.example.TeleMediaX.model.AuthenticationRequest;
import com.example.TeleMediaX.model.AuthenticationResponse;
import com.example.TeleMediaX.model.User;
import com.example.TeleMediaX.services.UserDaoService;
import com.example.TeleMediaX.services.UserService;
import com.example.TeleMediaX.utility.JWTUtility;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserResource {
    @Autowired
    UserDaoService service;

    @Autowired
    MessageSource messageSource;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JWTUtility jwtUtility;

    //@Autowired
    UserDetails userDetails;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){

        return service.findAll();
    }

    @GetMapping(path = "/users/{id}"/*, headers = "X-API-VERSION = 1"*/)
    public EntityModel<User> retrieveUsers(@PathVariable int id){
        User user = service.findOne(id);
        if(user==null)
            throw new UserNotFoundException("id"+id);
        EntityModel<User> entityModel = EntityModel.of(user);
            WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
            entityModel.add(link.withRel("all-users"));
        return entityModel;
    }


    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable int id) {

        service.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
    return ResponseEntity.created(location).build();
    }

    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default Message", locale);
        //return "Hello World V2";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e){
            throw new Exception("Incorrect Username or Password", e);
        }
        userService.loadUserByUsername(authenticationRequest.getUserName());

        System.out.println("user detail :: " + new Gson().toJson(userDetails));

        final String jwt = jwtUtility.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}

