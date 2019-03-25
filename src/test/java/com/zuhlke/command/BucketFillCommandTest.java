package com.zuhlke.command;

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
    private OutputStream os;
    private String br;

    @BeforeEach
    void setUp() {
        subject = new BucketFillCommand();

        //prepare to redirect output
        os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        br = System.getProperty("line.separator");
    }

    @Test
    void expectExceptionWhenMoreThanOneColorCharacter() {
        // then
        Assertions.assertThrows(InvalidInputException.class, () -> {
            // given
            String command = "B 10 1 oo";
            Canvas canvas = new Canvas(20, 4);

            //when
            subject.execute(command.split(" "), canvas);
        });
    }

    @Test
    void simpleFillImageTest() throws InvalidInputException {
        // given
        Canvas testCanvas = new Canvas(5, 3);
        testCanvas.plot(new Coordinate(1, 2), 'x');
        String command = "B 1 1 o";

        // when
        testCanvas = subject.execute(command.split(" "), testCanvas);
        testCanvas.print();

        // then
        String expected = br +
                "-------" + br +
                "|ooooo|" + br +
                "|xoooo|" + br +
                "|ooooo|" + br +
                "-------" + br;

        assertEquals(expected, os.toString());
    }

    @Test
    void bucketFillImageTest() throws InvalidInputException {
        // given
        String command = "B 1 1 o";
        String setupCommand = "R 14 1 18 3";
        Canvas canvas = new Canvas(20, 4);
        DrawRectangleCommand dR = new DrawRectangleCommand();
        canvas = dR.execute(setupCommand.split(" "), canvas);

        // when
        canvas = subject.execute(command.split(" "), canvas);
        canvas.print();

        // then
        String expected = br +
                "----------------------" + br +
                "|oooooooooooooxxxxxoo|" + br +
                "|ooooooooooooox   xoo|" + br +
                "|oooooooooooooxxxxxoo|" + br +
                "|oooooooooooooooooooo|" + br +
                "----------------------" + br;

        assertEquals(expected, os.toString());

    }

    @Test
    void bucketFillBorderExpectColorBorder() throws InvalidInputException {
        // given
        Canvas canvas = new Canvas(20, 4);
        DrawRectangleCommand dR = new DrawRectangleCommand();
        String command = "B 14 1 o";
        String setupCommand = "R 14 1 18 3";
        canvas = dR.execute(setupCommand.split(" "), canvas);

        // when
        canvas = subject.execute(command.split(" "), canvas);
        canvas.print();

        // then
        String expected = br +
                "----------------------" + br +
                "|             ooooo  |" + br +
                "|             o   o  |" + br +
                "|             ooooo  |" + br +
                "|                    |" + br +
                "----------------------" + br;

        assertEquals(expected, os.toString());
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }
}