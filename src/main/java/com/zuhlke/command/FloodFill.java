package com.zuhlke.command;

import com.zuhlke.exception.NoCanvasException;
import com.zuhlke.model.Canvas;

public class FloodFill implements Command {

    @Override
    public Canvas execute(String[] input, Canvas base) throws NoCanvasException {

        if (base == null) throw new NoCanvasException();

        return null;
    }
}
