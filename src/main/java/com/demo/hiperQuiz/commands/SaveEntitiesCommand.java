package com.demo.hiperQuiz.commands;

import com.demo.hiperQuiz.dao.PlayerRepository;
import com.demo.hiperQuiz.dao.QuizRepository;
import com.demo.hiperQuiz.dao.QuizResultRepository;
import com.demo.hiperQuiz.dao.UserRepository;
import com.demo.hiperQuiz.model.AllCollections;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

@Slf4j
public class SaveEntitiesCommand implements Command {
    private PlayerRepository playerRepository;
    private UserRepository userRepo;
    private QuizRepository quizRepository;
    private QuizResultRepository quizResultRepository;
    private OutputStream out;


    public SaveEntitiesCommand(OutputStream out,PlayerRepository playerRepository, UserRepository userRepo,
                               QuizRepository quizRepository, QuizResultRepository quizResultRepository) {
        this.playerRepository = playerRepository;
        this.userRepo = userRepo;
        this.quizRepository = quizRepository;
        this.quizResultRepository = quizResultRepository;
        this.out = out;
    }

    @Override
    public String execute() {
        AllCollections allCollections = new AllCollections(
                userRepo.findAll(),
                quizRepository.findAll(),
                playerRepository.findAll(),
                quizResultRepository.findAll());

        try(ObjectOutputStream oos = new ObjectOutputStream(out)){
            oos.writeObject(allCollections);
            return "All collections saved successfully";
        } catch (IOException e) {
            log.error("Error writing entities to file", e);
            return "Error writing collections to file";
        }
    }
}
