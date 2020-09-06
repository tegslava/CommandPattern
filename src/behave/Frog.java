package behave;

public class Frog {
    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 10;

    protected int position;

    public Frog() {
        position = 5;
    }

    public boolean jump(int steps) {

        if (((position + steps) > MAX_POSITION) || ((position + steps) < MIN_POSITION)) {
            return false;
        } else {
            position += steps;
            return true;
        }
    }

    public void drawPositon() {
        StringBuffer tmpl = new StringBuffer("012345678910");
        tmpl.setCharAt(this.position, '*');
        if (this.position == 10) tmpl.setCharAt(11, ' ');
        System.out.printf("%s\n", tmpl);
    }
}
