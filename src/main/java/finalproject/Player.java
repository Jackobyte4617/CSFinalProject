package finalproject;

public class Player implements Creature{
    private int hp;
    private static final int MAXHP = 100;
    private static final int DAMAGE = 150;
    private String name;
    private static int numberOfPlayers;

    public Player(String name){
        this.hp = MAXHP;
        numberOfPlayers++;
        this.name=name;
        //this.name = "Adventurer " + numberOfPlayers;
    }

    @Override
    public void attack(Creature c){
        c.takeDamage(DAMAGE);
    }
    public String getName(){return name;}

    @Override
    public int getHealth(){
        return hp;
    }

    @Override
    public int getMAXHP() {
        return MAXHP;
    }

    @Override
    public boolean isAlive(){
        return hp > 0;
    }

    @Override
    public void takeDamage(int damage){
        hp -= damage;
    }

    public int getDamage(){ return DAMAGE;}

    public boolean run(){
        return ((((double)MAXHP - hp) / MAXHP) >= Math.random());

    }

    public void getStatus(){
        System.out.println(hp + "   " + name);
    }
}
