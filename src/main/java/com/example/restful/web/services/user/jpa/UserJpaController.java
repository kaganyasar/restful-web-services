package com.example.restful.web.services.user.jpa;

import com.example.restful.web.services.user.data.User;
import com.example.restful.web.services.user.inmemory.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaController {
    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/jpa/users/retrieveAll")
    public List<User> retrieveAllUsers() {
        return userDaoService.retrieveAllUsers();
    }

    @GetMapping("/jpa/users/retrieve/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = userDaoService.retrieveUser(id);
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkTo.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/jpa/users/upsert")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
        User upsertedUser = userDaoService.upsertUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(upsertedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userDaoService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/jpa/users/greetings")
    public String getGreetingsMessage() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
}
