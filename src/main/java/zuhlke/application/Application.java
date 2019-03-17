package zuhlke.application;

import zuhlke.command.*;
import zuhlke.model.Canvas;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {

    private Canvas canvas;
    String[] input;
    Command currentCommand;
    Map<String, Command> CommandLibrary = new HashMap();

    public Application() {
        CommandLibrary.put("C", new SetCanvas());
        CommandLibrary.put("L", new DrawLine());
        CommandLibrary.put("R", new DrawRectangle());
        CommandLibrary.put("B", new BucketFill());
        CommandLibrary.put("Q", new ExitApp());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        // loop request for command input
        do {
            System.out.print("Enter command: ");
            input = scanner.nextLine().split(" ");
            try {
                currentCommand = CommandLibrary.get(input[0].toUpperCase());
                canvas = currentCommand.Execute(input, canvas);
            } catch (NullPointerException e) {
                System.out.println("Invalid Command. Please try again.");
            } catch (Exception e) {
                System.out.println("Error in command parameters: " + e.getMessage());
                System.out.println("Please try again");
            }

        } while (input[0] != null);

    }

}
