package finalproject;

import javafx.scene.control.Label;

/**
 * Base abstract class for all enemies, implementing the Combatant interface.
 */
public abstract class Enemy implements Creature {
    private String name;
    private int health;
    private Label enemyLabel;
    private String icon;
    private int damage;
    private final int MAXHP;

    public Enemy(String name, int health, int damage, String icon) {
        this.name = name;
        this.health = health;
        this.icon = icon;
        this.enemyLabel = new Label(icon + " " + name + " | Health: " + health);
        this.damage = damage;
        this.MAXHP = health;
    }

    @Override
    public void attack(Creature c){
        c.takeDamage(damage);
    }

    @Override
    public void takeDamage(int amount) {
        this.health -= amount;
        if (this.health < 0) {
            this.health = 0;
        }
        this.enemyLabel.setText(icon + " " + name + " | Health: " + health);
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getDamage(){ return damage;}

    @Override
    public int getMAXHP() { return MAXHP;}

    public Label getEnemyLabel() {
        return enemyLabel;
    }
}