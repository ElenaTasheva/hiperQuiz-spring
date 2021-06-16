package com.demo.hiperQuiz.dao.impl;


import com.demo.hiperQuiz.dao.KeyGenerator;
import com.demo.hiperQuiz.dao.PlayerRepository;
import com.demo.hiperQuiz.model.Player;


public class PlayerRepositoryImpl extends RepositoryMemoryImpl<Long, Player> implements
        PlayerRepository {

public PlayerRepositoryImpl(KeyGenerator<Long> keyGenerator) {
        super(keyGenerator);
        }
        }
