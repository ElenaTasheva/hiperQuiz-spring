package com.demo.hiperQuiz.web;


import com.demo.hiperQuiz.dao.UserRepository;
import com.demo.hiperQuiz.exception.InvalidEntityDataException;
import com.demo.hiperQuiz.model.User;
import com.demo.hiperQuiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
       return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        User registered = userService.createUser(user);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
                        .buildAndExpand(registered.getId()).toUri())
                .body(registered);
    }

    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public User updateUser(@PathVariable("id") Long id, @Valid @RequestBody User user) {
        if (!id.equals(user.getId())) {
            throw new InvalidEntityDataException(
                    String.format("ID in URL:'%s' is different from ID in request body ID:'%s'.",
                            id, user.getId())
            );
        }
        return userService.updated(user);
    }

    @DeleteMapping(path = "/{id}")
    public User delete(@PathVariable("id") Long id) {
        return userService.deleteById(id);
    }

}


