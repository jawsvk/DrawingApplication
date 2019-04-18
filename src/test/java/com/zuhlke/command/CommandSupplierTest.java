package com.zuhlke.command;

import com.zuhlke.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandSupplierTest {

    @Test
    void getCreateCanvasCommandFromInput() throws InvalidInputException {
        //given
        CommandSupplier supplier = new CommandSupplier();

        //when
        Command result = supplier.supplyCommand("C");

        //then
        assertEquals(result.getClass(), CreateCanvasCommand.class);

    }
}