package com.zuhlke.application;

import com.zuhlke.command.*;
import com.zuhlke.model.Canvas;

import java.util.HashMap;
import java.util.Scanner;

public class Application {

    private Canvas canvas;
    private String[] input;
    private Command currentCommand;
    private HashMap<String, Command> CommandLibrary = new HashMap<>();

    public Application() {
        CommandLibrary.put("C", new SetCanvas());
        CommandLibrary.put("L", new DrawLine());
        CommandLibrary.put("R", new DrawRectangle());
        CommandLibrary.put("B", new BucketFill());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        // loop request for command input
        do {
            System.out.print("Enter command: ");
            input = scanner.nextLine().split(" ");

            //exit statement
            if (input[0].toUpperCase().equals("Q")) return;

            //execute command
            try {
                currentCommand = CommandLibrary.get(input[0].toUpperCase());
                canvas = currentCommand.Execute(input, canvas);
            } catch (NullPointerException e) {
                if (canvas == null) System.out.println("No canvas found. Please create a canvas with C command");
                else System.out.println("Invalid Command. Please try again.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error in command parameters: " + e.getMessage());
                System.out.println("Please try again");
            }
        } while (!input[0].toUpperCase().equals("Q"));

        scanner.close();

    }

}
