package commands;

import classes.Frog;
import interfaces.FrogCommand;

public class JumpRightCommand implements FrogCommand {
    private Frog frog;
    private int steps;

    public JumpRightCommand(Frog frog, int steps) {
        this.frog = frog;
        this.steps = steps;
    }

    @Override
    public boolean doit() {
        return frog.jump(steps);
    }

    @Override
    public boolean undo() {
        return frog.jump(-steps);
    }
}