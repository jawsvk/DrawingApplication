package com.zuhlke.application;

import com.zuhlke.command.*;
import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Application {

    private Canvas canvas;
    private final HashMap<String, Command> commandLibrary = new HashMap<>();

    public Application() {
        commandLibrary.put("C", new CreateCanvasCommand());
        commandLibrary.put("L", new DrawLineCommand());
        commandLibrary.put("R", new DrawRectangleCommand());
        commandLibrary.put("B", new BucketFillCommand());
    }

    public void run() {
        String[] input;

        try (Scanner scanner = new Scanner(System.in)) {
            do {
                //loop request for command input
                System.out.print("Enter command: ");
                input = scanner.nextLine().split(" ");

                //filter out double-spaces
                input = Arrays.stream(input).filter(s -> !s.isEmpty()).toArray(String[]::new);

                //execute command
                if (commandLibrary.containsKey(input[0])) {
                    try {
                        canvas = commandLibrary.get(input[0]).execute(input, canvas);
                        canvas.print();
                    } catch (InvalidInputException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Please try again");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());

                    }
                } else if (!input[0].toUpperCase().equals("Q")) {
                    //print out if command is not found
                    System.out.println("Command not found. Please try again.");
                }
            } while (!input[0].toUpperCase().equals("Q"));
        }
    }


}


