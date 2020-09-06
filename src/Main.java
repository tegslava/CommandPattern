import behave.*;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private static int curCommand = -1;

    public static void main(String[] args) throws FileNotFoundException {
        Frog frog = new Frog();
        List<FrogCommand> commands = new ArrayList<>();
        Deque<FrogCommand> commandsRedo = new ArrayDeque<>();
        Integer curCommand = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.printf("------------------------------------------------------\nЛягушка\n"
                + "------------------------------------------------------\n");
        System.out.println("\nНачальна позиция лягушки:");
        frog.drawPositon();
        try {
            while (true) {
                System.out.println("\nВыберите действие:");
                MenuItems.showMenu();
                if (!procInput(scanner.nextLine(), commands, commandsRedo, frog)) {
                    frog.drawPositon();
                    //System.out.printf(" curCommand = %s %s\n", curCommand, commands.size());
                } else break;
            }
        } finally {
            scanner.close();
            System.out.println("\nПрограмма завершена.");
        }
    }

    private static boolean procInput(String nextLine, List<FrogCommand> commands, Deque<FrogCommand> commandsRedo, Frog frog) {
        FrogCommand cmd;

        MenuItems menuItem = MenuItems.getByStartString(nextLine);
        if (menuItem == null) {
            System.out.println("Команда не распознана");
            return false;
        }

        switch (menuItem) {
            case JUMP_LEFT:
                if (curCommand != commands.size() - 1) {
                    removeAbovecurCommand(commands);
                    commandsRedo.clear();
                }
                cmd = FrogCommands.jumpLeftCommand(frog, Integer.parseInt(nextLine));
                if (cmd.execute()) {
                    curCommand++;
                    commands.add(cmd);
                }
                break;
            case JUMP_RIGHT:
                if (curCommand != commands.size() - 1) {
                    removeAbovecurCommand(commands);
                    commandsRedo.clear();
                }
                cmd = FrogCommands.jumpRightCommand(frog, Integer.parseInt(nextLine));
                if (cmd.execute()) {
                    curCommand++;
                    commands.add(cmd);
                }
                break;
            case UNDO:
                System.out.println(">Отменить действие");
                if (curCommand < 0) {
                    System.out.println("Нечего отменять!");
                } else {
                    commandsRedo.addFirst(commands.get(curCommand));
                    commands.get(curCommand).undo();
                    //commands.remove(curCommand);
                    curCommand--;
                }
                break;
            case REDO:
                System.out.println(">Повторить отмененную команду");
                if (commandsRedo.size() == 0) {
                    System.out.println("Нечего повторять!");
                } else {
                    FrogCommand redoCommand = commandsRedo.pollFirst();
                    redoCommand.execute();
                    curCommand++;
                }
                break;
            case REPEAT:
                System.out.println(">Повторить последнее действие");
                if (curCommand < 0) {
                    System.out.println("Нечего повторять!");
                } else {
                    if (commands.get(curCommand).execute()) {
                        commands.add(commands.get(curCommand));
                        curCommand++;
                    }
                }
                break;
            case EXIT:
                System.out.println("Выход");
                return true;
            default:

        }
        return false;
    }

    private static void removeAbovecurCommand(List<FrogCommand> commands) {
        Iterator<FrogCommand> commIterator = commands.iterator();
        while (commIterator.hasNext()) {
            FrogCommand nextCommand = commIterator.next();
            if (commands.indexOf(nextCommand) > curCommand) {
                commIterator.remove();
            }
        }
    }

}
