package finalproject;

public class Player implements Creature{
    private int hp;
    private static final int MAXHP = 20;
    private static final int DAMAGE = 6;
    private String name;

    public Player(String name){
        this.hp = MAXHP;
        this.name = name;
    }

    @Override
    public int attack(){
        return DAMAGE;
    }
    public String getName(){
        return name;
    }
    
    @Override
    public int getHp(){
        return hp;
    }

    @Override
    public int getMaxHp() {
        return MAXHP;
    }

    @Override
    public boolean isDead(){
        return hp <= 0;
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
