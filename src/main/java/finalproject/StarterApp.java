package finalproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class StarterApp extends Application {

    private int currentEnemyIndex = 0;
    private int playerHealth = 100;

    @Override
    public void start(Stage stage) {
        Label titleLabel = new Label("FF1RipOff - Milestone 2: 1-on-1 Battle");

        // Player and Game State Labels
        Label playerLabel = new Label("👤 Player | Health: " + playerHealth);
        Label statusLabel = new Label("A wild enemy appears! Choose your action.");

        // Build your roster of enemies
        List<Enemy> enemyRoster = new ArrayList<>();
        enemyRoster.add(new Zombie());
        enemyRoster.add(new Werewolf());
        enemyRoster.add(new Ogre());
        enemyRoster.add(new Pirate());
        enemyRoster.add(new Dragon());
        enemyRoster.add(new Death());

        // Layout container for the single active enemy in the 1-on-1 fight
        VBox activeEnemyBox = new VBox(8);

        // Helper method to refresh the active 1-on-1 enemy display
        Runnable updateEnemyDisplay = () -> {
            activeEnemyBox.getChildren().clear();
            Enemy currentEnemy = enemyRoster.get(currentEnemyIndex);
            activeEnemyBox.getChildren().add(new Label("--- CURRENT OPPONENT ---"));
            activeEnemyBox.getChildren().add(currentEnemy.getEnemyLabel());
        };

        updateEnemyDisplay.run();

        // Attack Button (Core Workflow Action)
        Button attackButton = new Button("Attack Enemy (25 Damage)");
        attackButton.setOnAction(event -> {
            Enemy currentEnemy = enemyRoster.get(currentEnemyIndex);

            if (currentEnemy.isAlive()) {
                currentEnemy.takeDamage(25);
                if (currentEnemy.isAlive()) {
                    statusLabel.setText("You struck the " + currentEnemy.getName() + "!");
                } else {
                    statusLabel.setText("Victory! You defeated the " + currentEnemy.getName() + "!");
                }
            } else {
                statusLabel.setText(currentEnemy.getName() + " is already defeated. Switch to the next enemy!");
            }
            updateEnemyDisplay.run();
        });

        // Next Enemy Button (To move to the next 1-on-1 fight)
        Button nextEnemyButton = new Button("Next Encounter");
        nextEnemyButton.setOnAction(event -> {
            currentEnemyIndex = (currentEnemyIndex + 1) % enemyRoster.size();
            Enemy nextEnemy = enemyRoster.get(currentEnemyIndex);
            statusLabel.setText("Encountered a new foe: " + nextEnemy.getName() + "!");
            updateEnemyDisplay.run();
        });

        // Arrange everything neatly in a vertical layout
        VBox root = new VBox(15, titleLabel, playerLabel, statusLabel, activeEnemyBox, attackButton, nextEnemyButton);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 450, 380);
        stage.setTitle("FF1RipOff - Nathan's Branch (1-on-1 Battle)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}