package com.zuhlke.command;

import com.zuhlke.exception.InsufficientParametersException;
import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DrawRectangleCommandTest {

    private DrawRectangleCommand subject;
    private String br;
    private OutputStream outputStream;

    @BeforeEach
    void setUp() {
        subject = new DrawRectangleCommand();
        br = System.getProperty("line.separator");

        //prepare to redirect output
        outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);
    }

    @Test
    void drawRectangleImageTest() throws InvalidInputException {
        // given
        Canvas canvas = new Canvas(20, 4);
        String command = "R 14 1 18 3";

        // when
        canvas = subject.execute(command.split("\\s"), canvas);
        canvas.print();

        // then
        String expected = br +
                "----------------------" + br +
                "|             xxxxx  |" + br +
                "|             x   x  |" + br +
                "|             xxxxx  |" + br +
                "|                    |" + br +
                "----------------------" + br;

        assertEquals(expected, outputStream.toString());

    }

    @Test
    @Disabled
    void drawRectangleToOutOfBoundImageTest() throws InvalidInputException {
        // given
        Canvas canvas = new Canvas(20, 4);
        String command = "R 14 1 22 6";

        // when
        canvas = subject.execute(command.split("\\s"), canvas);
        canvas.print();

        // then
        String expected = br +
                "----------------------" + br +
                "|             xxxxxxx|" + br +
                "|             x      |" + br +
                "|             x      |" + br +
                "|             x      |" + br +
                "----------------------" + br;

        assertEquals(expected, outputStream.toString());

    }

    @Test
    void expectExceptionWhenInputParameterNotNumber() {
        // then
        Assertions.assertThrows(InvalidInputException.class, () -> {
            // given
            String command = "R 14 1 18 a";

            // when
            subject.validateInput(command.split("\\s"));
        });
    }

    @Test
    void expectExceptionWhenInsufficientInputParameters() {
        // then
        Assertions.assertThrows(InsufficientParametersException.class, () -> {
            // given
            String command = "R 14 1 17";

            // when
            subject.validateInput(command.split("\\s"));
        });
    }

    @AfterEach
    void tearDown() {
        //Restore normal output
        System.setOut(System.out);
    }
}