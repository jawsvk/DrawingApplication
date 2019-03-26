package com.zuhlke.command;

import com.zuhlke.exception.InsufficientParametersException;
import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BucketFillCommandTest {

    private BucketFillCommand subject;
    private OutputStream outputStream;
    private String br;

    @BeforeEach
    void setUp() {
        subject = new BucketFillCommand();

        //prepare to redirect output
        outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);

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
        testCanvas.print();

        // then
        String expected = br +
                "-------" + br +
                "|ooooo|" + br +
                "|xoooo|" + br +
                "|ooooo|" + br +
                "-------" + br;

        assertEquals(expected, outputStream.toString());
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
        canvas.print();

        // then
        String expected = br +
                "----------------------" + br +
                "|oooooooooooooxxxxxoo|" + br +
                "|ooooooooooooox   xoo|" + br +
                "|oooooooooooooxxxxxoo|" + br +
                "|oooooooooooooooooooo|" + br +
                "----------------------" + br;

        assertEquals(expected, outputStream.toString());

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
        canvas.print();

        // then
        String expected = br +
                "----------------------" + br +
                "|             ooooo  |" + br +
                "|             o   o  |" + br +
                "|             ooooo  |" + br +
                "|                    |" + br +
                "----------------------" + br;

        assertEquals(expected, outputStream.toString());
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

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }
}