package com.zuhlke.command;

import com.zuhlke.exception.InsufficientParametersException;
import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DrawLineCommandTest {

    private DrawLineCommand subject;
    private String br;

    @BeforeEach
    void setUp() {
        subject = new DrawLineCommand();
        br = System.getProperty("line.separator");

    }

    @Test
    void drawLineImageTest() throws InvalidInputException {
        // given
        Canvas canvas = new Canvas(20, 4);
        String command = "L 1 2 6 2";

        // when
        canvas = subject.execute(command.split("\\s+"), canvas);

        // then
        String expected =
                "----------------------" + br +
                "|                    |" + br +
                "|xxxxxx              |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;

        assertEquals(expected, canvas.toString());
    }

    @Test
    void drawLineInReverseImageTest() throws InvalidInputException {
        // given
        Canvas canvas = new Canvas(20, 4);
        String command = "L 6 2 1 2";

        // when
        canvas = subject.execute(command.split("\\s+"), canvas);

        // then
        String expected =
                "----------------------" + br +
                "|                    |" + br +
                "|xxxxxx              |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;

        assertEquals(expected, canvas.toString());
    }

    @Test
    void expectExceptionWhenNotVerticalOrHorizontalLine() {
        // then
        Assertions.assertThrows(InvalidInputException.class, () -> {
            // given
            Canvas canvas = new Canvas(20, 4);
            String command = "L 1 2 6 4";

            // when
            subject.execute(command.split("\\s+"), canvas);
        });
    }

    @Test
    void expectExceptionWhenInputParametersNotAllNumbers() {
        // then
        Assertions.assertThrows(InvalidInputException.class, () -> {
            // given
            String command = "L 1 a 6 v";

            // when
            subject.validateInput(command.split("\\s+"));
        });
    }

    @Test
    void expectExceptionWhenInputParametersHasNegativeNumbers() {
        // then
        Assertions.assertThrows(InvalidInputException.class, () -> {
            // given
            String command = "L -1 2 -6 2";

            // when
            subject.validateInput(command.split("\\s+"));
        });
    }

    @Test
    void expectExceptionWhenInsufficientInputParameters() {
        // then
        Assertions.assertThrows(InsufficientParametersException.class, () -> {
            // given
            String command = "L 1 2 6";

            // when
            subject.validateInput(command.split("\\s+"));
        });
    }

}