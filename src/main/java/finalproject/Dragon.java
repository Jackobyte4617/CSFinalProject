package finalproject;

public class Dragon extends Enemy {
    private static final int MAXHP = 300;
    private static final int damage = 12;

    public Dragon() {
        super("Drogon the Black Dread [BOSS]", MAXHP, damage, "🐉");
    }
}
