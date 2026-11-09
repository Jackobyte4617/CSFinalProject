package finalproject;

public class Werewolf extends Enemy {
    private static final int MAXHP = 60;
    private static final int damage = 4;
    public Werewolf() {
        super("Lycaon", MAXHP, damage, "🐺");
    }
}