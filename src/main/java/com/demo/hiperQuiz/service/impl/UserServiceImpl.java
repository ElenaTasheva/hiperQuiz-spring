package com.demo.hiperQuiz.service.impl;

import com.demo.hiperQuiz.dao.impl.UserRepositoryJpaImpl;
import com.demo.hiperQuiz.exception.EntityNotFoundException;
import com.demo.hiperQuiz.model.User;
import com.demo.hiperQuiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryJpaImpl userRepository;

    @Autowired
    public UserServiceImpl(UserRepositoryJpaImpl userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findByUserName(String userName){
        return this.userRepository.findByUsername(userName).orElseThrow(() ->
                new EntityNotFoundException(String.format("" +
                        "User with username %s does not exist", userName)));

    }

    @Override
    public User updated(User user) throws EntityNotFoundException {
        return this.userRepository.update(user);
    }

    @Override
    public User logIn(User user) {
        return null;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        user.setId(null);
        user.setCreated(LocalDateTime.now());
        return userRepository.create(user);
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User deleteById(Long id) {
          return   this.userRepository.deleteById(id);
    }

    @Override
    public void drop() {
         userRepository.drop();
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Product not found."));
    }

}
