package com.zuhlke.command;

import com.zuhlke.exception.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CommandSupplierTest {

    @Test
    void getCreateCanvasCommandFromInput() throws InvalidInputException {

        //when
        Command result = CommandSupplier.supplyCommand("C");

        //then
        assertEquals(result.getClass(), CreateCanvasCommand.class);

    }

    @Test
    void getRightCommandsBasedOnInput() throws InvalidInputException {

        //when
        Command result = CommandSupplier.supplyCommand("B");

        //then
        assertEquals(result.getClass(), BucketFillCommand.class);

    }

    @Test
    void expectExceptionWhenNoCommandFound() {
        // then
        Assertions.assertThrows(InvalidInputException.class, () -> {

            // when
            CommandSupplier.supplyCommand("X");
        });
    }
}