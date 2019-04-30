package com.zuhlke.application;

import com.zuhlke.model.Canvas;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationTest {

    private Application app;
    private String br;
    private OutputStream outputStream;


    @BeforeEach
    void setUp() {
        app = new Application();

        // redirect output stream
        outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);
        System.setOut(ps);

        br = System.getProperty("line.separator");

    }

    @Test
    void checkCreateCanvasCommand() {
        // given
        String command = "C 20 4" + br + "Q";
        sendToInput(command);

        // when
        app.run(null);

        // then
        String expected = "Enter command: " + br +
                "----------------------" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br +
                "Enter command: ";

        assertEquals(expected, outputStream.toString());
    }

    @Test
    void checkDrawLineCommand() {
        // given
        Canvas source = new Canvas(20, 4);
        String command = "L 1 2 6 2" + br + "Q";
        sendToInput(command);

        // when
        app.run(source);

        // then
        String expected = "Enter command: " + br +
                "----------------------" + br +
                "|                    |" + br +
                "|xxxxxx              |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br +
                "Enter command: ";


        assertEquals(expected, outputStream.toString());
    }

    @Test
    void checkDrawRectangleCommand() {
        // given
        Canvas source = new Canvas(20, 4);
        String command = "R 14 1 18 3" + br + "Q";
        sendToInput(command);

        // when
        app.run(source);

        // then
        String expected = "Enter command: " + br +
                "----------------------" + br +
                "|             xxxxx  |" + br +
                "|             x   x  |" + br +
                "|             xxxxx  |" + br +
                "|                    |" + br +
                "----------------------" + br +
                "Enter command: ";

        assertEquals(expected, outputStream.toString());
    }

    @Test
    void checkBucketFillCommand() {
        // given
        Canvas source = new Canvas(20, 4);
        String command = "B 1 1 o" + br + "Q";
        sendToInput(command);

        // when
        app.run(source);

        // then
        String expected = "Enter command: " + br +
                "----------------------" + br +
                "|oooooooooooooooooooo|" + br +
                "|oooooooooooooooooooo|" + br +
                "|oooooooooooooooooooo|" + br +
                "|oooooooooooooooooooo|" + br +
                "----------------------" + br +
                "Enter command: ";

        assertEquals(expected, outputStream.toString());
    }

    @Test
    void checkQuitApplication() {
        // given
        String command = "Q";
        sendToInput(command);

        // when
        app.run(null);

        // then
        String expected = "Enter command: ";
        assertEquals(expected, outputStream.toString());

    }

    @Test
    void expectExceptionWhenNoCanvasCreated() {
        // given
        String command = "L 1 2 6 2" + br + "Q";
        sendToInput(command);

        // when
        app.run(null);

        // then
        assertTrue(outputStream.toString().contains("No canvas found"));
    }

    @Test
    void expectExceptionWhenWrongCommand() {
        // given
        String command = "C 20 4" + br + "WrongCommand" + br + "Q";
        sendToInput(command);

        // when
        app.run(null);

        // then
        assertTrue(outputStream.toString().contains("Command not found"));
    }


    void sendToInput(String command) {
        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);
    }

    @AfterEach
    void tearDown() {
        //restore normal input and output
        System.setOut(System.out);
        System.setIn(System.in);
    }
}