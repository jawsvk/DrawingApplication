package com.zuhlke.command;

import com.zuhlke.model.Canvas;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BucketFillTest {

    private Canvas canvas;
    private Character[][] testbase;
    private BucketFill subject;
    private String linebreak;
    private String ans1;
    private String ans2;

    @Before
    public void setUp() {
        subject = new BucketFill();
        canvas = new Canvas(20, 4);
        linebreak = System.getProperty("line.separator");
        ans1 = linebreak +
                "----------------------" + linebreak +
                "|oooooooooooooxxxxxoo|" + linebreak +
                "|ooooooooooooox   xoo|" + linebreak +
                "|oooooooooooooxxxxxoo|" + linebreak +
                "|oooooooooooooooooooo|" + linebreak +
                "----------------------" + linebreak;

        ans2 = linebreak +
                "----------------------" + linebreak +
                "|             ooooo  |" + linebreak +
                "|             o   o  |" + linebreak +
                "|             ooooo  |" + linebreak +
                "|                    |" + linebreak +
                "----------------------" + linebreak;

    }

    @Test
    public void bucketFillImageTest() {
        String command = "B 10 1 o";
        String setupCommand = "R 14 1 18 3";

        OutputStream os = new ByteArrayOutputStream();

        DrawRectangle dR = new DrawRectangle();
        try {
            canvas = dR.Execute(setupCommand.split(" "), canvas);

            //prepare to redirect output
            PrintStream ps = new PrintStream(os);
            System.setOut(ps);

            canvas = subject.Execute(command.split(" "), canvas);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(ans1, os.toString());

    }

    @Test
    public void BucketFillBorderExpectColorBorder() {
        String command = "B 14 1 o";
        String setupCommand = "R 14 1 18 3";

        OutputStream os = new ByteArrayOutputStream();

        DrawRectangle dR = new DrawRectangle();
        try {
            canvas = dR.Execute(setupCommand.split(" "), canvas);

            //prepare to redirect output
            PrintStream ps = new PrintStream(os);
            System.setOut(ps);

            canvas = subject.Execute(command.split(" "), canvas);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(ans2, os.toString());
    }
}