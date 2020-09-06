package behave;

public class Command implements FrogCommand {

    private final Frog frog;
    private int steps;
    private String name;

    public Command(Frog frog, int steps, String name) {
        this.frog = frog;
        this.steps = steps;
        this.name = name;
    }

    public Command(Frog frog, int steps) {
        this.frog = frog;
        this.steps = steps;
    }

    @Override
    public boolean execute() {
        if (frog.jump(steps)) {
            System.out.printf(">%s:%d -> Ok\n", name, steps);
            return true;
        } else {
            System.out.printf(">%s:%d -> Cancel (Стенка)\n", name, steps);
            return false;
        }
    }

    @Override
    public boolean undo() {
        System.out.printf(">%s:%d -> Ok\n", name, steps);
        frog.jump(-steps);
        return false;
    }

}
