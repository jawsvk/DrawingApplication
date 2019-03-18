package com.zuhlke.command;

import com.zuhlke.model.Canvas;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExitAppTest {
    ExitApp subject;
    Canvas canvas;

    @Before
    public void setUp() {
        subject = new ExitApp();
        canvas = new Canvas(20, 4);
    }

    @Test
    public void checkExitCode0() {
        final int[] exitCode = new int[1];

        String[] command = new String[]{"q"};
        System.setSecurityManager(new SecurityManager() {
            @Override
            public void checkExit(int status) {
                exitCode[0] = status;
                throw new RuntimeException();
            }
        });

        try {
            subject.Execute(command, canvas);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(exitCode[0], 0);
    }


}