package com.zuhlke.command;

import com.zuhlke.model.Canvas;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;


public class SetCanvasTest {

    private Canvas canvas;
    private Character[][] testbase;
    private SetCanvas subject;
    private String linebreak;
    private String ans;

    @Before
    public void setUp() {
        canvas = new Canvas(20, 4);
        subject = new SetCanvas();
        linebreak = System.getProperty("line.separator");
        ans = linebreak +
                "----------------------" + linebreak +
                "|                    |" + linebreak +
                "|                    |" + linebreak +
                "|                    |" + linebreak +
                "|                    |" + linebreak +
                "----------------------" + linebreak;
    }

    @Test
    public void SetCanvasExpect44HorizontalBorderlines() {
        String command = "C 20 4";
        try {
            canvas = subject.Execute(command.split(" "), canvas);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        testbase = canvas.getBase();

        int count = 0;

        for (Character[] array : testbase) {
            for (Character c : array) {
                if (c == '-') count++;
            }
        }

        assertEquals(44, count);

    }

    @Test
    public void SetCanvasExpect8VerticalBorderlines() {
        String command = "C 20 4";
        try {
            canvas = subject.Execute(command.split(" "), canvas);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        testbase = canvas.getBase();

        int count = 0;

        for (Character[] array : testbase) {
            for (Character c : array) {
                if (c == '|') count++;
            }
        }

        assertEquals(8, count);

    }

    @Test
    public void setCanvasImageTest() {
        String command = "C 20 4";

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
}