package com.demo.hiperQuiz.service;

import com.demo.hiperQuiz.dao.QuizRepository;
import com.demo.hiperQuiz.model.Player;
import com.demo.hiperQuiz.model.Quiz;
import com.demo.hiperQuiz.model.User;

import java.util.Collection;
import java.util.Optional;

public interface QuizService {

    QuizRepository getQuizRepository();

    Optional<Quiz> findById(long quizId);

    Quiz create(Quiz quiz);

    Collection<?> findAll();

    Quiz createQuiz(User user);

    Player play(Player player);
}
