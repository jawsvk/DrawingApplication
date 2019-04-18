package com.zuhlke.application;

import com.zuhlke.command.Command;
import com.zuhlke.command.CommandSupplier;
import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;

import java.util.Scanner;

public class Application {

    private Canvas canvas;

    public Canvas run(Canvas source) {
        String[] input;
        String cmd;

        if (source != null) canvas = new Canvas(source);

        try (Scanner scanner = new Scanner(System.in)) {
            do {
                // loop request for command input
                System.out.print("Enter command: ");
                input = scanner.nextLine().split("\\s+");
                cmd = input[0].toUpperCase();

                // execute command
                try {
                    final Command command = CommandSupplier.supplyCommand(cmd);
                    command.validateInput(input);
                    canvas = command.execute(input, canvas);
                    System.out.println(canvas.toString());
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Please try again");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (!cmd.equals("Q"));
        }
        return canvas;
    }

}


