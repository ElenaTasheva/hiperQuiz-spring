package com.demo.hiperQuiz.dao;

@FunctionalInterface //SAM
public interface KeyGenerator<K> {
    K getNextId();
}
