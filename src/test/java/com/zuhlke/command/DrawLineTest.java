package com.zuhlke.command;

import com.zuhlke.model.Canvas;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DrawLineTest {

    private Canvas canvas;
    private Character[][] testbase;
    private DrawLine subject;
    private String linebreak;
    private String ans;

    @Before
    public void setUp() {
        canvas = new Canvas(20, 4);
        testbase = canvas.getBase();
        subject = new DrawLine();
        linebreak = System.getProperty("line.separator");
        ans = linebreak +
                "----------------------" + linebreak +
                "|                    |" + linebreak +
                "|xxxxxx              |" + linebreak +
                "|                    |" + linebreak +
                "|                    |" + linebreak +
                "----------------------" + linebreak;

    }

    @Test
    public void drawLineImageTest() {
        String command = "L 1 2 6 2";

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

    @Test
    public void drawLineInReverseImageTest() {
        Canvas testCanvas = new Canvas(20, 4);
        String command = "L 6 2 1 2";

        //prepare to redirect output
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        try {
            canvas = subject.Execute(command.split(" "), testCanvas);
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