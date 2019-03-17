package zuhlke.command;

import zuhlke.model.Canvas;

public interface Command {
    Canvas Execute(String[] input, Canvas base)throws Exception;

}
