package com.zuhlke.command;

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

class DrawLineCommandTest {

    private DrawLineCommand subject;
    private String br;
    private OutputStream os;

    @BeforeEach
    void setUp() {
        subject = new DrawLineCommand();
        br = System.getProperty("line.separator");

        //prepare to redirect output
        os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
    }

    @Test
    void drawLineImageTest() throws InvalidInputException {
        // given
        Canvas canvas = new Canvas(20, 4);
        String command = "L 1 2 6 2";

        // when
        canvas = subject.execute(command.split(" "), canvas);
        canvas.print();

        // then
        String expected = br +
                "----------------------" + br +
                "|                    |" + br +
                "|xxxxxx              |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;

        assertEquals(expected, os.toString());
    }

    @Test
    void drawLineInReverseImageTest() throws InvalidInputException {
        // given
        Canvas canvas = new Canvas(20, 4);
        String command = "L 6 2 1 2";

        // when
        canvas = subject.execute(command.split(" "), canvas);
        canvas.print();

        // then
        String expected = br +
                "----------------------" + br +
                "|                    |" + br +
                "|xxxxxx              |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;

        assertEquals(expected, os.toString());
    }


    @Test
    void checkVerticalOrHorizontalLine() {
        // then
        Assertions.assertThrows(InvalidInputException.class, () -> {
            // given
            Canvas canvas = new Canvas(20, 5);
            String command = "L 1 2 6 4";

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