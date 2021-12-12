package commands;

import classes.Frog;
import interfaces.FrogCommand;

public class JumpLeftCommand implements FrogCommand {
    private Frog frog;
    private int steps;

    public JumpLeftCommand(Frog frog, int steps) {
        this.frog = frog;
        this.steps = steps;
    }

    @Override
    public boolean doit() {
        return frog.jump(-steps);
    }

    @Override
    public boolean undo() {
        return frog.jump(steps);
    }
}