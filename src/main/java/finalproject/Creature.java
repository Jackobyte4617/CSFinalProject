package finalproject;

public interface Creature {
    public void attack(Creature c);
    public void takeDamage(int damage);
    public boolean isAlive();
    public int getHealth();
    public String getName();
    public int getMAXHP();
    public int getDamage();

}
