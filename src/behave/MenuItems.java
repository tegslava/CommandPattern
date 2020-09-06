package behave;

public enum MenuItems {
    JUMP_RIGHT("+N - прыгни на N шагов направо;^\\+\\d+$"),
    JUMP_LEFT("-N - прыгни на N шагов налево;^\\-\\d+$"),
    UNDO("<< - Undo (отмени последнюю команду);^<<$"),
    REDO(">> - Redo (повтори отменённую команду);^>>$"),
    REPEAT("!! - повтори последнюю команду;^!!$"),
    EXIT(" 0 - выход;^0$");

    private String comment;

    MenuItems(String comment) {
        this.comment = comment;
    }

    public static MenuItems getByComment(String comment) {
        for (MenuItems g : MenuItems.values()) {
            if (g.comment.equals(comment)) return g;
        }
        return null;
    }

    public static MenuItems getByStartString(String input) {
        if (input == null) return null;
        for (MenuItems g : MenuItems.values()) {
            String[] ptr = g.comment.split(";");
            if (input.trim().matches(ptr[1])) return g;
        }
        return null;
    }

    public static void showMenu() {
        MenuItems[] menuItems = MenuItems.values();
        for (int i = 0; i < menuItems.length; i++) {
            String[] cmntStr = (menuItems[i].comment.split(";"));
            System.out.printf("%s\n", cmntStr[0]);
        }
    }

    public static MenuItems getByOrdinal(int item) {
        for (MenuItems g : MenuItems.values()) {
            if (g.ordinal() == item) return g;
        }
        return null;
    }

    @Override
    public String toString() {
        return comment;
    }
}

