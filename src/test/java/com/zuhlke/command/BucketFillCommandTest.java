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

    private Canvas canvas;
    private BucketFillCommand subject;
    private String ans1;
    private String ans2;
    private String ans3;
    private OutputStream os;

    @BeforeEach
    void setUp() {
        canvas = new Canvas(20, 4);
        subject = new BucketFillCommand();

        //prepare to redirect output
        os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        String br = System.getProperty("line.separator");
        ans1 = br +
                "----------------------" + br +
                "|oooooooooooooxxxxxoo|" + br +
                "|ooooooooooooox   xoo|" + br +
                "|oooooooooooooxxxxxoo|" + br +
                "|oooooooooooooooooooo|" + br +
                "----------------------" + br;

        ans2 = br +
                "----------------------" + br +
                "|             ooooo  |" + br +
                "|             o   o  |" + br +
                "|             ooooo  |" + br +
                "|                    |" + br +
                "----------------------" + br;
        ans3 = br +
                "-------" + br +
                "|ooooo|" + br +
                "|xoooo|" + br +
                "|ooooo|" + br +
                "-------" + br;
    }

    @Test
    void checkInvalidColorCharacterError() {
        Assertions.assertThrows(InvalidInputException.class, () -> {
            String command = "B 10 1 |";
            subject.execute(command.split(" "), canvas);
        });
    }

    @Test
    void expectExceptionWhenMoreThanOneColorCharacter() {
        Assertions.assertThrows(InvalidInputException.class, () -> {
            String command = "B 10 1 oo";
            subject.execute(command.split(" "), canvas);
        });
    }

    @Test
    void simpleFillImageTest() {
        Canvas testCanvas = new Canvas(5, 3);
        testCanvas.plot(new Coordinate(1, 2), 'x');
        String command = "B 1 1 o";

        subject = new BucketFillCommand();


        try {
            testCanvas = subject.execute(command.split(" "), testCanvas);
            testCanvas.print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(ans3, os.toString());
    }

    @Test
    void bucketFillImageTest() {
        String command = "B 1 1 o";
        String setupCommand = "R 14 1 18 3";
        Canvas canvas = new Canvas(20, 4);

        DrawRectangleCommand dR = new DrawRectangleCommand();
        subject = new BucketFillCommand();
        try {
            canvas = dR.execute(setupCommand.split(" "), canvas);

            canvas = subject.execute(command.split(" "), canvas);
            canvas.print();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(ans1, os.toString());

    }

    @Test
    void bucketFillBorderExpectColorBorder() {
        String command = "B 14 1 o";
        String setupCommand = "R 14 1 18 3";

        DrawRectangleCommand dR = new DrawRectangleCommand();
        try {
            canvas = dR.execute(setupCommand.split(" "), canvas);

            canvas = subject.execute(command.split(" "), canvas);
            canvas.print();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(ans2, os.toString());
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }
}