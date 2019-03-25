package com.zuhlke.application;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationScenarioTest {
    private Application app;
    private OutputStream os;
    private String br;

    @BeforeEach
    void setUp() {
        app = new Application();
        br = System.getProperty("line.separator");

        //redirect output stream
        os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
    }

    @Test
    void checkAllCommandsInSequence() throws IOException, URISyntaxException {
        URI uri = new URI("");

        final URL resource = this.getClass().getClassLoader().getResource("AllCommandsInSequence.txt");

        if (resource != null) {
            uri = resource.toURI();
        }

        byte[] bytes = Files.readAllBytes(Paths.get(uri));


        String output = new String(bytes);

        //set input stream
        String command = "C 20 4" + br +
                "L 1 2 6 2" + br +
                "L 6 3 6 4" + br +
                "R 14 1 18 3" + br +
                "B 10 1 o" + br +
                "Q";

        InputStream is = new ByteArrayInputStream(command.getBytes());
        System.setIn(is);

        app.run(null);

        //convert LF to CRLF
        final String expected = output.replaceAll("\\n", "\r\n");
        assertEquals(expected, os.toString());
    }

    @AfterEach
    void tearDown() {
        //restore normal input and output
        System.setOut(System.out);
        System.setIn(System.in);
    }

}
