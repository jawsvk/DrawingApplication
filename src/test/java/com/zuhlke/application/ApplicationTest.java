package com.zuhlke.application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


class ApplicationTest {

    private Application app;
    private String br;

    @BeforeEach
    void setUp() {
        app = new Application();
        br = System.getProperty("line.separator");
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

        assertTrue(os.toString().contains("No canvas found"));
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