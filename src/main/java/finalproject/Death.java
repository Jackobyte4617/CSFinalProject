package finalproject;

public class Death extends Enemy {
    private static final int MAXHP = 500;
    private static final int damage = 20;

    public Death() {
        super("DEATH [FINAL BOSS]", MAXHP, damage, "💀");
    }
}
