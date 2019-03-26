package com.zuhlke.command;

import com.zuhlke.exception.InsufficientParametersException;
import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateCanvasCommandTest {

    private CreateCanvasCommand subject;
    private String br;
    private OutputStream outputStream;

    @BeforeEach
    void setUp() {
        subject = new CreateCanvasCommand();
        br = System.getProperty("line.separator");

        //prepare to redirect output
        outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);
    }

    @Test
    void createCanvasImageTest() {
        // given
        String command = "C 20 4";
        Canvas canvas;

        // when
        canvas = subject.execute(command.split("\\s+"), null);
        canvas.print();

        // then
        String expected = br +
                "----------------------" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;

        assertEquals(expected, outputStream.toString());
    }

    @Test
    void expectExceptionWhenParametersNotNumbers() {
        // then
        Assertions.assertThrows(InvalidInputException.class, () -> {
            // given
            String command = "C b a";

            // when
            subject.validateInput(command.split("\\s+"));
        });
    }

    @Test
    void expectExceptionWhenParametersHasNegativeNumber() {
        // then
        Assertions.assertThrows(InvalidInputException.class, () -> {
            // given
            String command = "C 20 -5";

            // when
            subject.validateInput(command.split("\\s+"));
        });
    }

    @Test
    void expectExceptionWhenInsufficientCanvasParameters() {
        // then
        Assertions.assertThrows(InsufficientParametersException.class, () -> {
            // given
            String command = "C 20";

            // when
            subject.validateInput(command.split("\\s+"));
        });
    }

    @AfterEach
    void tearDown() {
        //Restore normal output
        System.setOut(System.out);
    }
}