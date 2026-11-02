package finalproject;

public class Ogre extends Enemy {
    private static final int MAXHP = 100;
    private static final int damage = 5;
    public Ogre() {
        super("Thrag", MAXHP, damage, "👹");
    }
}
