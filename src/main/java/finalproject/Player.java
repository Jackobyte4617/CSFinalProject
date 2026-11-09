package finalproject;

public class Player implements Creature{
    private int hp;
    private static final int MAXHP = 100;
    private static final int DAMAGE = 6;
    private String name;
    private static int numberOfPlayers;

    public Player(){
        this.hp = MAXHP;
        numberOfPlayers++;
        this.name = "Adventurer " + numberOfPlayers;
    }

    @Override
    public int attack(){
        return DAMAGE;
    }
    public String getName(){


        return name;
    }

    @Override
    public int getHealth(){
        return hp;
    }

    public int getMaxHp() {
        return MAXHP;
    }

    @Override
    public boolean isAlive(){
        return hp = 0;
    }

    @Override
    public void takeDamage(int damage){
        hp -= damage;
    }

    public boolean run(){
        return (((MAXHP - hp) / MAXHP) <= Math.random());

    }

    public void getStatus(){
        System.out.println(hp + "   " + name);
    }
}
