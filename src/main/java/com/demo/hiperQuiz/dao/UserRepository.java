package com.demo.hiperQuiz.dao;


import com.demo.hiperQuiz.exception.EntityNotFoundException;
import com.demo.hiperQuiz.model.User;

import java.util.Optional;

// todo check if we need separate repos for Player and Administrator
public interface UserRepository extends Repository<Long, User> {

    Optional<User> findByUsername(String userName) throws EntityNotFoundException;
}
