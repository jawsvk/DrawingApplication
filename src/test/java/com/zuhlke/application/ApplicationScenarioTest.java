package com.zuhlke.application;


import com.zuhlke.model.Canvas;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationScenarioTest {

    private Application app;
    private String br;

    @BeforeEach
    void setUp() {
        app = new Application();
        br = System.getProperty("line.separator");

    }

    @Test
    void checkAllCommandsInSequence() {

        // given
        Canvas result;
        String command = "C 20 4" + br +
                "L 1 2 6 2" + br +
                "L 6 3 6 4" + br +
                "R 14 1 18 3" + br +
                "B 10 1 o" + br +
                "Q";

        sendToInput(command);


        // when
        result = app.run(null);

        // then
        String expected =
                "----------------------" + br +
                        "|oooooooooooooxxxxxoo|" + br +
                        "|xxxxxxooooooox   xoo|" + br +
                        "|     xoooooooxxxxxoo|" + br +
                        "|     xoooooooooooooo|" + br +
                        "----------------------" + br;
        assertEquals(expected, result.toString());
    }

    void sendToInput(String command) {
        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);
    }

    @AfterEach
    void tearDown() {
        //restore normal input
        System.setIn(System.in);
    }

}
