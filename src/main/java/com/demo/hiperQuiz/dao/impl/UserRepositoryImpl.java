package com.demo.hiperQuiz.dao.impl;


import com.demo.hiperQuiz.dao.KeyGenerator;
import com.demo.hiperQuiz.dao.UserRepository;
import com.demo.hiperQuiz.model.User;

import java.util.Optional;


public class UserRepositoryImpl extends RepositoryMemoryImpl<Long, User> implements
        UserRepository {

        public UserRepositoryImpl(KeyGenerator<Long> keyGenerator) {
                super(keyGenerator);
        }

        @Override
        public Optional<User> findByUsername(String userName) {
               return super.findAll().stream().filter(user -> user.getUsername().equals(userName))
                       .findFirst();

        }


}
