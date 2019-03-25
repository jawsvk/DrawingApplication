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
    private OutputStream os;
    private Canvas source;

    @BeforeEach
    void setUp() {
        app = new Application();
        source = new Canvas(20, 4);

        //redirect output stream
        os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        br = System.getProperty("line.separator");

    }

    @Test
    void checkCreateCanvasCommand() {
        String ans = br +
                "----------------------" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;

        //set input stream
        String command = "C 20 4" + br + "Q";

        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        app.run(null);

        int startFrom = os.toString().indexOf(":") + 2;
        assertEquals(ans, os.toString().substring(startFrom, startFrom + ans.length()));
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
        String command = "L 1 2 6 2" + br + "Q";

        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        app.run(source);

        int startFrom = os.toString().indexOf(":") + 2;

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
        String command = "R 14 1 18 3" + br + "Q";

        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        app.run(source);

        int startFrom = os.toString().indexOf(":") + 2;

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
        String command = "B 1 1 o" + br + "Q";

        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);


        app.run(source);

        int startFrom = os.toString().indexOf(":") + 2;

        assertEquals(ans, os.toString().substring(startFrom, startFrom + ans.length()));
    }

    @Test
    void checkQuitApplication() {
        //set input stream
        String command = "Q";

        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        app.run(null);

        int startFrom = os.toString().indexOf(":") + 2;

        assertTrue(os.toString().substring(startFrom).isEmpty());
    }

    @Test
    void checkForNoCanvasError() {
        //set input stream
        String command = "L 1 2 6 2" + br + "Q";
        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        app.run(null);

        assertTrue(os.toString().contains("No canvas found"));
    }

    @Test
    void checkForInvalidCommandError() {
        //set input stream
        String command = "C 20 4" + br + "WrongCommand" + br + "Q";
        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        app.run(null);

        assertTrue(os.toString().contains("Command not found"));
    }

    @Test
    void checkForInvalidParameters() {
        //set input stream
        String command = "C 20 4" + br + "L -1 2 6 2" + br + "Q";
        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);
        app.run(null);

        assertTrue(os.toString().contains("Either Start Point or End Point (or both) are out of bounds"));
    }

    @AfterEach
    void tearDown() {
        //restore normal input and output
        System.setOut(System.out);
        System.setIn(System.in);
    }
}