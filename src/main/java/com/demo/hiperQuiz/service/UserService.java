package com.demo.hiperQuiz.service;

import com.demo.hiperQuiz.exception.EntityNotFoundException;
import com.demo.hiperQuiz.model.User;

import java.util.List;

public interface UserService {

    User findByUserName(String userName) throws EntityNotFoundException;

    User updated(User user) throws EntityNotFoundException;

    User logIn(User user);

    User createUser(User user);

    List<User> findAllUsers();

    User deleteById(Long id);

    void drop();

    User findById(Long id);


}
