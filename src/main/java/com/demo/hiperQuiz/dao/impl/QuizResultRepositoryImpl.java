package com.demo.hiperQuiz.dao.impl;



import com.demo.hiperQuiz.dao.KeyGenerator;
import com.demo.hiperQuiz.dao.QuizResultRepository;
import com.demo.hiperQuiz.model.QuizResult;

public class QuizResultRepositoryImpl extends RepositoryMemoryImpl<Long, QuizResult>
        implements QuizResultRepository {


    public QuizResultRepositoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
    }
}
