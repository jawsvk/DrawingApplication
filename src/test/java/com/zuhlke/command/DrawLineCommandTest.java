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
import static org.junit.jupiter.api.Assertions.fail;

class DrawLineCommandTest {

    private Canvas canvas;
    private DrawLineCommand subject;
    private String br;

    @BeforeEach
    void setUp() {
        canvas = new Canvas(20, 4);
        subject = new DrawLineCommand();
        br = System.getProperty("line.separator");


    }

    @Test
    void drawLineImageTest() {
        String ans = br +
                "----------------------" + br +
                "|                    |" + br +
                "|xxxxxx              |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;

        String command = "L 1 2 6 2";

        //prepare to redirect output
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        try {
            canvas = subject.execute(command.split(" "), canvas);
            canvas.print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail(e.getCause());

        }

        assertEquals(ans, os.toString());
    }

    @Test
    void drawLineInReverseImageTest() {
        String ans = br +
                "----------------------" + br +
                "|                    |" + br +
                "|xxxxxx              |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;

        String command = "L 6 2 1 2";

        //prepare to redirect output
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        try {
            canvas = subject.execute(command.split(" "), canvas);
            canvas.print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail(e.getCause());

        }

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