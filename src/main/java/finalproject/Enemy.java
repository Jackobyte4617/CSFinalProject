package finalproject;

import javafx.scene.control.Label;

/**
 * Base abstract class for all enemies, implementing the Combatant interface.
 */
public abstract class Enemy implements Combatant {
    protected String name;
    protected int health;
    protected Label enemyLabel;
    protected String icon;

    public Enemy(String name, int health, String icon) {
        this.name = name;
        this.health = health;
        this.icon = icon;
        this.enemyLabel = new Label(icon + " " + name + " | Health: " + health);
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