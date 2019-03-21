package com.zuhlke.command;

import com.zuhlke.model.Canvas;

public interface Command {
    Canvas execute(String[] input, Canvas source) throws Exception;

}
