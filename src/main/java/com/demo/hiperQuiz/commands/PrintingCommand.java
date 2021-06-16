package com.demo.hiperQuiz.commands;

import java.util.Scanner;

public class PrintingCommand {

    private Scanner scanner;



    public PrintingCommand() {
        this.scanner = new Scanner(System.in);
    }



    public void print(String text){
        System.out.println(text);
    }
}
