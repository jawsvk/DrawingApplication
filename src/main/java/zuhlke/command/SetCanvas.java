package zuhlke.command;

import zuhlke.model.Canvas;

public class SetCanvas implements Command {

    @Override
    public Canvas Execute(String[] input, Canvas base) throws Exception {
        int x = Integer.parseInt(input[1]);
        int y = Integer.parseInt(input[2]);

        //minimum canvas size is 1,1
        if (x <= 0 || y <= 0) {
            throw new Exception("Invalid Parameters >> Width and Height must be more than zero");
        }

        base = new Canvas(x, y);
        base.print();

        return base;
    }
}
