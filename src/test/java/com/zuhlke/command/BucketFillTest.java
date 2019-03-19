package com.zuhlke.command;

import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;
import org.junit.After;
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
    private String ans3;

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

        ans3 = linebreak +
                "-------" + linebreak +
                "|ooooo|" + linebreak +
                "|xoooo|" + linebreak +
                "|ooooo|" + linebreak +
                "-------" + linebreak;
    }

    @Test
    public void bucketFillImageTest() {
        String command = "B 1 1 o";
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

    @Test
    public void SimpleTestCase() {

        Canvas testCanvas = new Canvas(5, 3);
        testCanvas.plot(new Coordinate(1, 2), 'x');
        String command = "B 1 1 o";

        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        try {
            canvas = subject.Execute(command.split(" "), testCanvas);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(ans3, os.toString());
    }

    @After
    public void tearDown() {
        System.setOut(System.out);
        System.setIn(System.in);
    }
}