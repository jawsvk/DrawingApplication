package com.zuhlke.application;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ApplicationTest {

    private Application app;
    private String br;
    private String step1;


    @BeforeEach
    private void setUp() {
        app = new Application();
        br = System.getProperty("line.separator");
        step1 = br +
                "----------------------" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;
    }

    @Test
    void checkSetCanvasCommand() {

        //set input stream
        String command = "C 20 4" + br + "Q";

        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        //redirect output stream
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        app.run();

        int startFrom = os.toString().indexOf(br);

        assertEquals(step1, os.toString().substring(startFrom, startFrom + step1.length()));
    }

    @Test
    void checkDrawLineCommand() {
        String ans = br +
                "----------------------" + br +
                "|                    |" + br +
                "|xxxxxx              |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------";

        //set input stream
        String command = "C 20 4" + br + "L 1 2 6 2" + br + "Q";

        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        //redirect output stream
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        app.run();

        int startFrom = os.toString().indexOf(":", step1.length()) + 2;

        assertEquals(ans, os.toString().substring(startFrom, startFrom + ans.length()));
    }

    @Test
    void checkDrawRectangleCommand() {
        String ans = br +
                "----------------------" + br +
                "|             xxxxx  |" + br +
                "|             x   x  |" + br +
                "|             xxxxx  |" + br +
                "|                    |" + br +
                "----------------------";

        //set input stream
        String command = "C 20 4" + br + "R 14 1 18 3" + br + "Q";

        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        //redirect output stream
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        app.run();

        int startFrom = os.toString().indexOf(":", step1.length()) + 2;

        assertEquals(ans, os.toString().substring(startFrom, startFrom + ans.length()));
    }

    @Test
    void checkBucketFillCommand() {
        String ans = br +
                "----------------------" + br +
                "|oooooooooooooooooooo|" + br +
                "|oooooooooooooooooooo|" + br +
                "|oooooooooooooooooooo|" + br +
                "|oooooooooooooooooooo|" + br +
                "----------------------";

        //set input stream
        String command = "C 20 4" + br + "B 1 1 o" + br + "Q";

        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        //redirect output stream
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        app.run();

        int startFrom = os.toString().indexOf(":", step1.length()) + 2;

        assertEquals(ans, os.toString().substring(startFrom, startFrom + ans.length()));
    }

    @Test
    void checkAllCommandsInSequence() {

        String ans = br +
                "----------------------" + br +
                "|oooooooooooooxxxxxoo|" + br +
                "|xxxxxxooooooox   xoo|" + br +
                "|     xoooooooxxxxxoo|" + br +
                "|     xoooooooooooooo|" + br +
                "----------------------" + br;

        //set input stream
        String command = "C 20 4" + br +
                "L 1 2 6 2" + br +
                "L 6 3 6 4" + br +
                "R 14 1 18 3" + br +
                "B 10 1 o" + br +
                "Q";

        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        //redirect output stream
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        app.run();

        int startFrom = os.toString().indexOf(":", step1.length() * 4) + 2;

        assertEquals(ans, os.toString().substring(startFrom, startFrom + ans.length()));
    }

    @Ignore
    @Test
    void checkQuitApplicationCommand() {
        //set input stream
//        String command = "
//                "Q";
    }

    @Test
    void checkForNoCanvasError() {
        //set input stream
        String command = "L 1 2 6 2" + br + "Q";
        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        //redirect output stream
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        app.run();
    }

    @Test
    void checkForInvalidCommandError() {
        //set input stream
        String command = "C 20 4" + br + "WrongCommand" + br + "Q";
        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        //redirect output stream
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        app.run();

        assertTrue(os.toString().contains("Command not found"));
    }

    @Test
    void checkForInvalidParameters() {
        //set input stream
        String command = "C 20 4" + br + "L -1 2 6 2" + br + "Q";
        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        //redirect output stream
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        app.run();

        assertTrue(os.toString().contains("Either Start Point or End Point (or both) are out of bounds"));
    }

    @AfterEach
    void tearDown() {
        //restore normal input and output
        System.setOut(System.out);
        System.setIn(System.in);
    }
}