package zuhlke.command;

import org.junit.Before;
import org.junit.Test;
import zuhlke.model.Canvas;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DrawRectangleTest {

    Canvas canvas;
    DrawRectangle subject;

    @Before
    public void setUp() {
        subject = new DrawRectangle();
        canvas = new Canvas(20, 4);
    }

    @Test
    public void drawRectangleImageTest() {
        String command = "R 14 1 18 3";

        //prepare to redirect output
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        try {
            canvas = subject.Execute(command.split(" "), canvas);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String ans = System.getProperty("line.separator") +
                "----------------------" + System.getProperty("line.separator") +
                "|             xxxxx  |" + System.getProperty("line.separator") +
                "|             x   x  |" + System.getProperty("line.separator") +
                "|             xxxxx  |" + System.getProperty("line.separator") +
                "|                    |" + System.getProperty("line.separator") +
                "----------------------" + System.getProperty("line.separator");

        assertEquals(ans, os.toString());

    }

}