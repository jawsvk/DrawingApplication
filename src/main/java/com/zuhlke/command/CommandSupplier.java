package com.zuhlke.command;

import com.zuhlke.exception.InvalidInputException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CommandSupplier {
    private static final Map<String, Supplier<Command>> COMMAND_SUPPLIER;

    static {
        final Map<String, Supplier<Command>> commands = new HashMap<>();
        commands.put("C", CreateCanvasCommand::new);
        commands.put("L", DrawLineCommand::new);
        commands.put("R", DrawRectangleCommand::new);
        commands.put("B", BucketFillCommand::new);

        COMMAND_SUPPLIER = Collections.unmodifiableMap(commands);
    }

    public static Command supplyCommand(String code) throws InvalidInputException {
        Supplier<Command> command = COMMAND_SUPPLIER.get(code);
        if (command == null) throw new InvalidInputException("Command not found.");
        return command.get();
    }
}

