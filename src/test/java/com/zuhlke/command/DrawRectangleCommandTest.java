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

class DrawRectangleCommandTest {

    private DrawRectangleCommand subject;
    private String br;
    private OutputStream os;

    @BeforeEach
    void setUp() {
        subject = new DrawRectangleCommand();
        br = System.getProperty("line.separator");

        //prepare to redirect output
        os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
    }

    @Test
    void drawRectangleImageTest() throws InvalidInputException {
        // given
        Canvas canvas = new Canvas(20, 4);
        String command = "R 14 1 18 3";

        // when
        canvas = subject.execute(command.split(" "), canvas);
        canvas.print();

        // then
        String expected = br +
                "----------------------" + br +
                "|             xxxxx  |" + br +
                "|             x   x  |" + br +
                "|             xxxxx  |" + br +
                "|                    |" + br +
                "----------------------" + br;

        assertEquals(expected, os.toString());

    }

    @Test
    void expectExceptionWhenInvalidInputParameters() {
        // then
        Assertions.assertThrows(InvalidInputException.class, () -> {
            // given
            Canvas canvas = new Canvas(20, 4);
            String command = "R 14 1 18 a";

            // when
            subject.execute(command.split(" "), canvas);
        });
    }

    @Test
    void expectExceptionWhenInsufficientInputParameters() {
        // then
        Assertions.assertThrows(InsufficientParametersException.class, () -> {
            // given
            Canvas canvas = new Canvas(20, 4);
            String command = "R 14 1 17";

            // when
            subject.execute(command.split(" "), canvas);
        });
    }

    @AfterEach
    void tearDown() {
        //Restore normal output
        System.setOut(System.out);
    }
}