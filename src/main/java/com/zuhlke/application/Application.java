package com.zuhlke.application;

import com.zuhlke.command.*;
import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.exception.NoCanvasException;
import com.zuhlke.model.Canvas;

import java.util.HashMap;
import java.util.Scanner;

public class Application {

    private Canvas canvas;
    private HashMap<String, Command> commandLibrary = new HashMap<>();

    public Application() {
        commandLibrary.put("C", new SetCanvas());
        commandLibrary.put("L", new DrawLine());
        commandLibrary.put("R", new DrawRectangle());
        commandLibrary.put("B", new BucketFill());
    }

    public void run() {
        String[] input;
        Scanner scanner = new Scanner(System.in);

        do {
            // loop request for command input
            System.out.print("Enter command: ");
            input = scanner.nextLine().toUpperCase().split(" ");

            //execute command
            if (commandLibrary.containsKey(input[0])) {
                try {
                    canvas = commandLibrary.get(input[0]).execute(input, canvas);
                    canvas.print();
                } catch (NoCanvasException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Please create a canvas with command C");
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Please try again");
                } catch (Exception e) {
                    System.out.println("Error in command parameters: " + e.getMessage());
                    System.out.println("Please try again");
                }
            } else {
                //print out if command is not found
                System.out.println("Command not found. Please try again.");
            }
        } while (!input[0].toUpperCase().equals("Q"));
        scanner.close();

    }


}


