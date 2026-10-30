package finalproject;

/**
 * Common interface for all combat-capable entities (Player, Enemy, Creature).
 */
public interface Combatant {
    String getName();
    int getHealth();
    void takeDamage(int amount);
    boolean isAlive();
}
