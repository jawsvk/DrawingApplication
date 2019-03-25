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

    private Canvas canvas;
    private DrawLineCommand subject;
    private String br;
    private OutputStream os;

    @BeforeEach
    void setUp() {
        canvas = new Canvas(20, 4);
        subject = new DrawLineCommand();
        br = System.getProperty("line.separator");

        //prepare to redirect output
        os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
    }

    @Test
    void drawLineImageTest() throws InvalidInputException {
        String ans = br +
                "----------------------" + br +
                "|                    |" + br +
                "|xxxxxx              |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;

        String command = "L 1 2 6 2";

        canvas = subject.execute(command.split(" "), canvas);
        canvas.print();

        assertEquals(ans, os.toString());
    }

    @Test
    void drawLineInReverseImageTest() throws InvalidInputException {
        String ans = br +
                "----------------------" + br +
                "|                    |" + br +
                "|xxxxxx              |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;

        String command = "L 6 2 1 2";

        canvas = subject.execute(command.split(" "), canvas);
        canvas.print();

        assertEquals(ans, os.toString());
    }


    @Test
    void checkVerticalOrHorizontalLine() {
        Assertions.assertThrows(InvalidInputException.class, () -> {
            String command = "L 1 2 6 4";
            subject.execute(command.split(" "), canvas);
        });
    }

    @AfterEach
    void tearDown() {
        //Restore normal output
        System.setOut(System.out);
    }
}