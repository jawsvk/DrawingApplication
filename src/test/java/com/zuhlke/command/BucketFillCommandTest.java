package com.zuhlke.command;

import com.zuhlke.exception.InsufficientParametersException;
import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BucketFillCommandTest {

    private BucketFillCommand subject;
    private String br;

    @BeforeEach
    void setUp() {
        subject = new BucketFillCommand();
        br = System.getProperty("line.separator");
    }


    @Test
    void simpleFillImageTest() throws InvalidInputException {
        // given
        Canvas testCanvas = new Canvas(5, 3);
        testCanvas.plot(new Coordinate(1, 2), 'x');
        String command = "B 1 1 o";

        // when
        testCanvas = subject.execute(command.split("\\s+"), testCanvas);

        // then
        String expected =
                "-------" + br +
                "|ooooo|" + br +
                "|xoooo|" + br +
                "|ooooo|" + br +
                "-------" + br;

        assertEquals(expected, testCanvas.toString());
    }

    @Test
    void bucketFillImageTest() throws InvalidInputException {
        // given
        String command = "B 1 1 o";
        String setupCommand = "R 14 1 18 3";
        Canvas canvas = new Canvas(20, 4);
        DrawRectangleCommand dR = new DrawRectangleCommand();
        canvas = dR.execute(setupCommand.split("\\s+"), canvas);

        // when
        canvas = subject.execute(command.split("\\s+"), canvas);

        // then
        String expected =
                "----------------------" + br +
                "|oooooooooooooxxxxxoo|" + br +
                "|ooooooooooooox   xoo|" + br +
                "|oooooooooooooxxxxxoo|" + br +
                "|oooooooooooooooooooo|" + br +
                "----------------------" + br;

        assertEquals(expected, canvas.toString());

    }

    @Test
    void bucketFillBorderExpectColorBorder() throws InvalidInputException {
        // given
        Canvas canvas = new Canvas(20, 4);
        DrawRectangleCommand dR = new DrawRectangleCommand();
        String command = "B 14 1 o";
        String setupCommand = "R 14 1 18 3";
        canvas = dR.execute(setupCommand.split("\\s+"), canvas);

        // when
        canvas = subject.execute(command.split("\\s+"), canvas);

        // then
        String expected =
                "----------------------" + br +
                "|             ooooo  |" + br +
                "|             o   o  |" + br +
                "|             ooooo  |" + br +
                "|                    |" + br +
                "----------------------" + br;

        assertEquals(expected, canvas.toString());
    }

    @Test
    void expectExceptionWhenMoreThanOneColorCharacter() {
        // then
        Assertions.assertThrows(InvalidInputException.class, () -> {
            // given
            String command = "B 10 1 oo";

            //when
            subject.validateInput(command.split("\\s+"));
        });
    }

    @Test
    void expectExceptionWhenInvalidCoordinateParameters() {
        // then
        Assertions.assertThrows(InvalidInputException.class, () -> {
            // given
            String command = "B 10 -1 o";

            //when
            subject.validateInput(command.split("\\s+"));
        });
    }

    @Test
    void expectExceptionWhenInsufficientParameters() {
        // then
        Assertions.assertThrows(InsufficientParametersException.class, () -> {
            // given
            String command = "B 10 1";

            //when
            subject.validateInput(command.split("\\s+"));
        });
    }

}