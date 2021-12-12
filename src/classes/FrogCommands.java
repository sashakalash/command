package classes;

import commands.JumpLeftCommand;
import commands.JumpRightCommand;
import interfaces.FrogCommand;

public class FrogCommands {
    public static FrogCommand jumpRightCommand(Frog frog, int steps) {
        // возвращаете объект команды, у которого
        // если вызвать .doit(), то лягушка её выполнит,
        // если вызвать .undo(), то лягушка её отменит
        return new JumpRightCommand(frog, steps);
    }

    public static FrogCommand jumpLeftCommand(Frog frog, int steps) {
        // возвращаете объект команды, у которого
        // если вызвать .doit(), то лягушка её выполнит,
        // если вызвать .undo(), то лягушка её отменит
        return new JumpLeftCommand(frog, steps);
    }
}