package finalproject;

import javafx.scene.control.Label;

/**
 * Base abstract class for all enemies, implementing the Combatant interface.
 */
public abstract class Enemy implements Creature {
    protected String name;
    protected int health;
    protected Label enemyLabel;
    protected String icon;
    protected int damage;

    public Enemy(String name, int health, int damage, String icon) {
        this.name = name;
        this.health = health;
        this.icon = icon;
        this.enemyLabel = new Label(icon + " " + name + " | Health: " + health);
        this.damage = damage;
    }

    @Override
    public int attack(){
        return damage;
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

    public Label getEnemyLabel() {
        return enemyLabel;
    }
}