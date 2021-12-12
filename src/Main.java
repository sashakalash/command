import classes.Frog;
import classes.FrogCommands;
import interfaces.FrogCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    public static final ArrayList<String> FIELD = new ArrayList<>();
    public static Frog frog;
    public static List<FrogCommand> commands;
    private static final String EMPTY_CELL_SYMBOL = "_";
    private static final String FROG_SYMBOL = "Ж";

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        commands = new ArrayList<>();
        frog = new Frog();

        fillTheField(frog.getPosition());
        int curCommand = -1;
        showField();
        while (true) {
            System.out.println("\nВведите команду:\n" +
                    "+N - прыгни на N шагов направо\n" +
                    "-N - прыгни на N шагов налево\n" +
                    "<< - Undo (отмени последнюю команду)\n" +
                    ">> - Redo (повтори отменённую команду)\n" +
                    "!! - повтори последнюю команду\n" +
                    "0 - выход");

            String command = scanner.nextLine();
            if ("0".equals(command)) {
                break;
            }

            if ("<<".equals(command)) {
                if (curCommand < 0) {
                    System.out.println("Нечего отменять!");
                } else {
                    commands.get(curCommand).undo();
                    curCommand--;
                }
            } else if (">>".equals(command)) {
                if (curCommand == commands.size() - 1) {
                    System.out.println("Нечего повторять!");
                } else {
                    curCommand++;
                    commands.get(curCommand).doit();
                }
            } else if ("!!".equals(command)) {
                if (curCommand < 0) {
                    System.out.println("Нечего повторять!");
                } else {
                    commands.get(curCommand).doit();
                }
            } else if (!command.startsWith("+") || !command.startsWith("-")) {
                System.out.println("Введена неверная команда! Повторите ввод");
                showField();
                continue;
            } else { //пользователь ввёл новое движение для лягушки
                if (curCommand != commands.size() - 1) {
                    //удаляем все команды которые были отменены
                    commands = removeCommandsFromList(commands, curCommand);
                }
                int steps = Character.getNumericValue(command.charAt(1));
                FrogCommand cmd = command.startsWith("+") ? FrogCommands.jumpRightCommand(frog, steps) : FrogCommands.jumpLeftCommand(frog, steps);
                curCommand++;
                commands.add(cmd);
                cmd.doit();
            }
            refillTheField(frog.getPosition());
            showField();
        }
    }

    public static void fillTheField(int position) {
        for (int i = 0; i < 11; i++) {
            if (i == position) {
                FIELD.add(FROG_SYMBOL);
            } else {
                FIELD.add(EMPTY_CELL_SYMBOL);
            }
        }
    }

    public static void refillTheField(int position) {
        for (int i = 0; i < 11; i++) {
                FIELD.set(i, EMPTY_CELL_SYMBOL);
        }
        FIELD.set(position, FROG_SYMBOL);
    }

    public static void showField() {
        FIELD.stream().forEach(System.out::print);
    }

    public static List<FrogCommand> removeCommandsFromList(List<FrogCommand> commands, int index) {
        for (int i = index; i < commands.size(); i++) {
            commands.remove(i);
        }
        return commands;
    }
}