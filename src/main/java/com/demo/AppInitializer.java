package com.demo;

import com.demo.hiperQuiz.Engine;
import com.demo.hiperQuiz.dao.impl.UserRepositoryJpaImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppInitializer {
    public static void main(String[] args) {


        SpringApplication.run(AppInitializer.class, args);

//        Engine engine = new Engine();
//        engine.run();


    }
}



