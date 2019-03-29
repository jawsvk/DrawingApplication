package com.zuhlke.application;

import com.zuhlke.command.*;
import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Application {

    private Canvas currentCanvas;

    private final HashMap<String, Command> commandLibrary = new HashMap<>();

    public Application() {
        commandLibrary.put("C", new CreateCanvasCommand());
        commandLibrary.put("L", new DrawLineCommand());
        commandLibrary.put("R", new DrawRectangleCommand());
        commandLibrary.put("B", new BucketFillCommand());
    }

    public void run(Canvas source) {
        String[] input;
        String cmd;
        Stack<Canvas> canvasStack = new Stack<>();
        Stack<Canvas> removedCanvasStack = new Stack<>();

        if (source != null) {
            currentCanvas = new Canvas(source);
        }

        canvasStack.push(currentCanvas);

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
                        currentCanvas = command.execute(input, canvasStack.peek());
                        canvasStack.push(currentCanvas).print();
                    } catch (InvalidInputException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Please try again");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (cmd.equals("UNDO")) {
                    undo(canvasStack, removedCanvasStack);
                } else if (cmd.equals("REDO")) {
                    redo(canvasStack, removedCanvasStack);
                } else if (!cmd.equals("Q")) {
                    // print out if command is not found
                    System.out.println("Command not found. Please try again.");
                }
            } while (!cmd.equals("Q"));
        }
    }

    private void undo(Stack<Canvas> canvasStack, Stack<Canvas> removedCanvas) {
        try {
            removedCanvas.push(canvasStack.pop());
            canvasStack.peek().print();
        } catch (Exception e) {
            System.out.println("\r\nNo more previous commands.");
        }
    }

    private void redo(Stack<Canvas> canvasStack, Stack<Canvas> removedCanvas) {
        try {
            canvasStack.push(removedCanvas.pop()).print();
        } catch (EmptyStackException e) {
            System.out.println("\r\nLatest command reached.");
        }
    }
}


