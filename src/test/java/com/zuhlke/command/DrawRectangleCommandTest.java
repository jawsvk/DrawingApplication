package com.zuhlke.command;

import com.zuhlke.exception.InsufficientParametersException;
import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DrawRectangleCommandTest {

    private DrawRectangleCommand subject;
    private String br;

    @BeforeEach
    void setUp() {
        subject = new DrawRectangleCommand();
        br = System.getProperty("line.separator");
    }

    @Test
    void drawRectangleImageTest() throws InvalidInputException {
        // given
        Canvas canvas = new Canvas(20, 4);
        String command = "R 14 1 18 3";

        // when
        canvas = subject.execute(command.split("\\s"), canvas);

        // then
        String expected =
                "----------------------" + br +
                "|             xxxxx  |" + br +
                "|             x   x  |" + br +
                "|             xxxxx  |" + br +
                "|                    |" + br +
                "----------------------" + br;

        assertEquals(expected, canvas.toString());

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

}