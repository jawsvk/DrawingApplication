package zuhlke.command;

import zuhlke.model.Canvas;

public class ExitApp implements Command {
    @Override
    public Canvas Execute(String[] input, Canvas base) throws Exception {
        Runtime.getRuntime().exit(0);
        return null;
    }
}
