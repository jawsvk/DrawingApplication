package com.zuhlke.command;

import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class SetCanvasTest {

    private Canvas canvas;
    private SetCanvas subject;
    private String br;
    private String ans;

    @Before
    public void setUp() {
        canvas = new Canvas(20, 4);
        subject = new SetCanvas();
        br = System.getProperty("line.separator");
        ans = br +
                "----------------------" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;
    }

    @Test
    public void setCanvasImageTest() {
        String command = "C 20 4";

        //prepare to redirect output
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        try {
            canvas = subject.execute(command.split(" "), canvas);
            canvas.print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(ans, os.toString());
    }

    @Test(expected = InvalidInputException.class)
    public void checkInvalidInputError() {
        String command = "C 20 -4";
        subject.execute(command.split(" "), canvas);
    }

    @After
    public void tearDown() {
        //Restore normal output
        System.setOut(System.out);
    }
}