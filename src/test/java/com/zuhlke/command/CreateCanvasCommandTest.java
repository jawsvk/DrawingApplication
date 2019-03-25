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
    private OutputStream os;

    @BeforeEach
    void setUp() {
        subject = new CreateCanvasCommand();
        br = System.getProperty("line.separator");

        //prepare to redirect output
        os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
    }

    @Test
    void createCanvasImageTest() throws InvalidInputException {
        // given
        String command = "C 20 4";
        Canvas canvas;

        // when
        canvas = subject.execute(command.split(" "), null);
        canvas.print();

        // then
        String expected = br +
                "----------------------" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;

        assertEquals(expected, os.toString());
    }

    @Test
    void expectExceptionWhenInvalidCanvasParameters() {
        // then
        Assertions.assertThrows(InvalidInputException.class, () -> {
            // given
            String command = "C 20 a";

            // when
            subject.execute(command.split(" "), null);
        });
    }

    @Test
    void expectExceptionWhenInsufficientCanvasParameters() {
        // then
        Assertions.assertThrows(InsufficientParametersException.class, () -> {
            // given
            String command = "C 20";

            // when
            subject.execute(command.split(" "), null);
        });
    }

    @AfterEach
    void tearDown() {
        //Restore normal output
        System.setOut(System.out);
    }
}