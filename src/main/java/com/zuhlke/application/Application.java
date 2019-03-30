package com.zuhlke.application;

import com.zuhlke.command.*;
import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Application {

    private Canvas currentCanvas;
    private Stack<Canvas> canvasStack;

    private final HashMap<String, Command> commandLibrary = new HashMap<>();

    public Application() {
        commandLibrary.put("C", new CreateCanvasCommand());
        commandLibrary.put("L", new DrawLineCommand());
        commandLibrary.put("R", new DrawRectangleCommand());
        commandLibrary.put("B", new BucketFillCommand());
        canvasStack = new Stack<>();
    }

    public void run(Canvas source) {
        String[] input;
        String cmd;

        if (source != null) {
            currentCanvas = new Canvas(source);
            canvasStack.push(currentCanvas);
        }

        try (Scanner scanner = new Scanner(System.in)) {
            do {
                // loop request for command input
                System.out.print("Enter command: ");
                input = scanner.nextLine().split("\\s+");
                cmd = input[0].toUpperCase();

                // execute command
                if (commandLibrary.containsKey(cmd)) {
                    try {
                        final Command command = commandLibrary.get(cmd);
                        command.validateInput(input);
                        currentCanvas = command.execute(input, currentCanvas);
                        canvasStack.push(currentCanvas);
                        currentCanvas.print();
                    } catch (InvalidInputException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Please try again");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (cmd.equals("UNDO")) {
                    canvasStack.pop();
                    currentCanvas = new Canvas(canvasStack.peek());
                    currentCanvas.print();
                } else if (!cmd.equals("Q")) {
                    // print out if command is not found
                    System.out.println("Command not found. Please try again.");
                }
            } while (!cmd.equals("Q"));
        }
    }


}


