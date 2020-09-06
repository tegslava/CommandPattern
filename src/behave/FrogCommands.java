package behave;

public class FrogCommands {
    public static FrogCommand jumpRightCommand(Frog frog, int steps) {
        return new Command(frog, steps, "Прыжок вправо");
    }

    public static FrogCommand jumpLeftCommand(Frog frog, int steps) {
        return new Command(frog, steps, "Прыжок влево");
    }

}
