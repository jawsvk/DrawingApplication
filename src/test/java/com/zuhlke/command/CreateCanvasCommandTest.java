package com.zuhlke.command;

import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateCanvasCommandTest {

    private Canvas canvas;
    private CreateCanvasCommand subject;
    private String br;

    @BeforeEach
    void setUp() {
        canvas = new Canvas(20, 4);
        subject = new CreateCanvasCommand();
        br = System.getProperty("line.separator");
    }

    @Test
    void setCanvasImageTest() throws InvalidInputException {
        String ans = br +
                "----------------------" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;

        String command = "C 20 4";

        //prepare to redirect output
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        canvas = subject.execute(command.split(" "), canvas);
        canvas.print();

        assertEquals(ans, os.toString());
    }

    @Test
    void checkValidCanvasParameters() {
        Assertions.assertThrows(InvalidInputException.class, () -> {
            String command = "C 20 -5";
            subject.execute(command.split(" "), canvas);
        });
    }

    @AfterEach
    void tearDown() {
        //Restore normal output
        System.setOut(System.out);
    }
}