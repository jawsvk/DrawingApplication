package com.zuhlke.application;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;


public class ApplicationTest {

    private Application app;
    private String br;

    @Before
    public void setUp() {
        app = new Application();
        br = System.getProperty("line.separator");
    }

    @Test
    public void checkForNoCanvasError() {
        //set input stream
        String command = "L 1 2 6 2" + br + "Q";
        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        //redirect output stream
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        app.run();

        assertTrue(os.toString().contains("No canvas found"));
    }

    @Test
    public void checkForInvalidCommandError() {
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
    public void checkForInvalidParameters() {
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

    @After
    public void tearDown() {
        //restore normal input and output
        System.setOut(System.out);
        System.setIn(System.in);
    }
}