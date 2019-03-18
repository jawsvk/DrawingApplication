package com.zuhlke.command;

import com.zuhlke.model.Canvas;

public interface Command {
    Canvas Execute(String[] input, Canvas base)throws Exception;

}
