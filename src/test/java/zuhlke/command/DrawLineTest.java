package zuhlke.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zuhlke.model.Canvas;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DrawLineTest {

    Canvas canvas;
    Character[][] testbase;
    DrawLine subject;

    @Before
    public void setUp() {
        Canvas canvas = new Canvas(20, 4);
        testbase = canvas.getBase();
        subject = new DrawLine();

    }

    @Test
    public void drawLineImageTest() {
        Canvas testCanvas = new Canvas(20, 4);
        String command = "L 1 2 6 2";

        //prepare to redirect output
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        try {
            canvas = subject.Execute(command.split(" "), testCanvas);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String ans = System.getProperty("line.separator") +
                "----------------------" + System.getProperty("line.separator") +
                "|                    |" + System.getProperty("line.separator") +
                "|xxxxxx              |" + System.getProperty("line.separator") +
                "|                    |" + System.getProperty("line.separator") +
                "|                    |" + System.getProperty("line.separator") +
                "----------------------" + System.getProperty("line.separator");

        assertEquals(ans, os.toString());
    }

    @Test
    public void drawLineInReverseImageTest() {
        Canvas testCanvas = new Canvas(20, 4);
        String command = "L 6 2 1 2";

        //prepare to redirect output
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        try {
            canvas = subject.Execute(command.split(" "), testCanvas);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String ans = System.getProperty("line.separator") +
                "----------------------" + System.getProperty("line.separator") +
                "|                    |" + System.getProperty("line.separator") +
                "|xxxxxx              |" + System.getProperty("line.separator") +
                "|                    |" + System.getProperty("line.separator") +
                "|                    |" + System.getProperty("line.separator") +
                "----------------------" + System.getProperty("line.separator");

        assertEquals(ans, os.toString());
    }

    @After
    public void tearDown() {
        //Restore normal output
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }
}