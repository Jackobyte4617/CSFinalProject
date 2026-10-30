package finalproject;

public class Player implements Creature{
    private int hp;
    private static final int DAMAGE = 6;
    private String name;

    public Player(String name){
        this.hp = 20;
        this.name = name;
    }

    @Override
    public int attack(){
        return DAMAGE;
    }
    public String getName(){
        return name;
    }
    public int getHp(){
        return hp;
    }

    @Override
    public void takeDamage(int damage){
        hp -= damage;
    }

    public boolean run(){
        return (.6 < Math.random());

    }

    public void getStatus(){
        System.out.println(hp + "   " + name);
    }
}
