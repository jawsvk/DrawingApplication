package com.zuhlke.command;

import com.zuhlke.model.Canvas;

public class ExitApp implements Command {
    @Override
    public Canvas Execute(String[] input, Canvas base) {
        Runtime.getRuntime().exit(0);
        return null;
    }
}
