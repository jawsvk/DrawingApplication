package com.zuhlke.command;

import com.zuhlke.model.Canvas;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DrawRectangleTest {

    private Canvas canvas;
    private DrawRectangle subject;
    private String br;
    private String ans;

    @Before
    public void setUp() {
        subject = new DrawRectangle();
        canvas = new Canvas(20, 4);
        br = System.getProperty("line.separator");
        ans = br +
                "----------------------" + br +
                "|             xxxxx  |" + br +
                "|             x   x  |" + br +
                "|             xxxxx  |" + br +
                "|                    |" + br +
                "----------------------" + br;
    }

    @Test
    public void drawRectangleImageTest() {
        String command = "R 14 1 18 3";

        //prepare to redirect output
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        try {
            canvas = subject.Execute(command.split(" "), canvas);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(ans, os.toString());

    }

    @After
    public void tearDown() {
        //Restore normal output
        System.setOut(System.out);
    }
}