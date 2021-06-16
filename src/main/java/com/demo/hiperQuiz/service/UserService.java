package com.demo.hiperQuiz.service;

import com.demo.hiperQuiz.exception.EntityNotFoundException;
import com.demo.hiperQuiz.model.User;

public interface UserService {

    User findByUserName(String userName) throws EntityNotFoundException;

    void updatePassword(User user, String password) throws EntityNotFoundException;

    User logIn(User user);

    User createUser();
}
