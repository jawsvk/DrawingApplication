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
import static org.junit.jupiter.api.Assertions.fail;

class CreateCanvasCommandTest {

    private Canvas canvas;
    private CreateCanvasCommand subject;
    private String ans;

    @BeforeEach
    void setUp() {
        canvas = new Canvas(20, 4);
        subject = new CreateCanvasCommand();
        String br = System.getProperty("line.separator");
        ans = br +
                "----------------------" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "|                    |" + br +
                "----------------------" + br;
    }

    @Test
    void setCanvasImageTest() {
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
            fail(e.getCause());
        }

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