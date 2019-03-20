package com.zuhlke.command;

import com.zuhlke.model.Canvas;
import org.junit.After;
import org.junit.Before;

public class FloodFillTest {

    private Canvas canvas;
    private FloodFill subject;

    @Before
    public void setUp() {
        canvas = new Canvas(20, 4);
        subject = new FloodFill();
    }

    @After
    public void tearDown() {
    }
}