package zuhlke.command;

import org.junit.Before;
import org.junit.Test;
import zuhlke.model.Canvas;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BucketFillTest {

    Canvas canvas;
    Character[][] testbase;
    BucketFill subject;

    @Before
    public void setUp() {
        subject = new BucketFill();
        canvas = new Canvas(20, 4);
    }

    @Test
    public void bucketFillImageTest() {
        String command = "B 10 3 o";
        String setupCommand = "R 14 1 18 3";

        OutputStream os = new ByteArrayOutputStream();

        DrawRectangle dR = new DrawRectangle();
        try {
            canvas = dR.Execute(setupCommand.split(" "), canvas);

            //prepare to redirect output
            PrintStream ps = new PrintStream(os);
            System.setOut(ps);

            canvas = subject.Execute(command.split(" "), canvas);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String ans = System.getProperty("line.separator") +
                "----------------------" + System.getProperty("line.separator") +
                "|oooooooooooooxxxxxoo|" + System.getProperty("line.separator") +
                "|ooooooooooooox   xoo|" + System.getProperty("line.separator") +
                "|oooooooooooooxxxxxoo|" + System.getProperty("line.separator") +
                "|oooooooooooooooooooo|" + System.getProperty("line.separator") +
                "----------------------" + System.getProperty("line.separator");

        assertEquals(ans, os.toString());

    }

    @Test
    public void BucketFillBorderExpectColorBorder() {
        String command = "B 14 1 o";
        String setupCommand = "R 14 1 18 3";

        OutputStream os = new ByteArrayOutputStream();

        DrawRectangle dR = new DrawRectangle();
        try {
            canvas = dR.Execute(setupCommand.split(" "), canvas);

            //prepare to redirect output
            PrintStream ps = new PrintStream(os);
            System.setOut(ps);

            canvas = subject.Execute(command.split(" "), canvas);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String ans = System.getProperty("line.separator") +
                "----------------------" + System.getProperty("line.separator") +
                "|             ooooo  |" + System.getProperty("line.separator") +
                "|             o   o  |" + System.getProperty("line.separator") +
                "|             ooooo  |" + System.getProperty("line.separator") +
                "|                    |" + System.getProperty("line.separator") +
                "----------------------" + System.getProperty("line.separator");

        assertEquals(ans, os.toString());
    }
}