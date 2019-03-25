package com.zuhlke.command;

import com.zuhlke.model.Canvas;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DrawRectangleCommandTest {

    private Canvas canvas;
    private DrawRectangleCommand subject;
    private String br;

    @BeforeEach
    void setUp() {
        canvas = new Canvas(20, 4);
        subject = new DrawRectangleCommand();
        br = System.getProperty("line.separator");
    }

    @Test
    void drawRectangleImageTest() {
        String ans = br +
                "----------------------" + br +
                "|             xxxxx  |" + br +
                "|             x   x  |" + br +
                "|             xxxxx  |" + br +
                "|                    |" + br +
                "----------------------" + br;
        String command = "R 14 1 18 3";

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

    @AfterEach
    void tearDown() {
        //Restore normal output
        System.setOut(System.out);
    }
}