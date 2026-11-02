package finalproject;

public class Zombie extends Enemy {
    private static final int MAXHP = 30;
    private static final int damage = 2;
    public Zombie() {
        super("Shambler", MAXHP, damage, "🧟");
    }
}