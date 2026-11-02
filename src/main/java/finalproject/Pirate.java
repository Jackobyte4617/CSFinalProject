package finalproject;

public class Pirate extends Enemy {
    private static final int MAXHP = 150;
    private static final int damage = 6;
    public Pirate() {
        super("Captain Bloodfang [MINI BOSS]", MAXHP, damage, "🏴‍☠️");
    }
}
