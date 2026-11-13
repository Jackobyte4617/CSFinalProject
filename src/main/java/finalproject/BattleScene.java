package finalproject;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.util.Duration;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;

public class BattleScene extends Application {

    Player p;
    boolean ranAway = false;
    boolean pAction = true;
    Enemy enemy;

    private HealthBar hE1;
    private HealthBar hP1;
    private Text console;
    private PauseTransition pause;
    private PauseTransition render;
    private Button attack;
    private Button run;

    // sprites + attack animation
    private static final String SPRITE_FOLDER = "/Images/";  // files live directly in src/resources/
    private static final int SPRITE_SIZE = 192;
    private static final double LUNGE_DISTANCE = 240;
    private Node enemySprite;
    private Node playerSprite;

    public BattleScene(Player p, Enemy e) {
        this.p = p;
        this.enemy = e;
    }

    @Override
    public void start(Stage stage) {
        // enemy sprite
        enemySprite = loadSprite(enemySpritePath(), new Color(.9, .3, .2, 1));
        enemySprite.setLayoutX(64);
        enemySprite.setLayoutY(8);

        // player sprite (art faces right, player is on the right, so flip it)
        playerSprite = loadSprite(SPRITE_FOLDER + "playersprite.jpg", new Color(.2, .3, .9, 1));
        playerSprite.setLayoutX(574);
        playerSprite.setLayoutY(8);
        playerSprite.setScaleX(-1);

        Rectangle box = new Rectangle(0, 80, 800, 320);

        hE1 = new HealthBar(40, 128, enemy);
        hP1 = new HealthBar(40, 328, p);

        Text playerHPLabel = new Text(60, 310, "Player");
        playerHPLabel.setFont(new Font(20));
        playerHPLabel.setFill(new Color(0.8, 0.8, 0.8, 1));

        Text enemyHPLabel = new Text(60, 110, "Enemy");
        enemyHPLabel.setFont(new Font(20));
        enemyHPLabel.setFill(new Color(0.8, 0.8, 0.8, 1));

        console = new Text(180, 210, "An " + enemy.getName() + " is attacking you, start battle!");
        console.setFont(new Font(20));
        console.setFill(new Color(0.8, 0.8, 0.8, 1));

        attack = new Button("Attack");
        attack.setTranslateX(600);
        attack.setTranslateY(100);
        attack.setFont(new Font(20));
        attack.setOnAction(e -> {
            if (pAction) {
                pAction = false;
                animateAttack(playerSprite, enemySprite, -1);
                p.attack(enemy);
                updateScreen(stage);
                console.setText(p.getName() + " damages " + enemy.getName() + " for " + p.getDamage());
                pause.setOnFinished(efe -> {
                    if (enemy.isAlive()) {
                        enemyAttack(stage);
                    }
                });
                pause.play();
            }
        });

        run = new Button("RUN!");
        run.setTranslateX(600);
        run.setTranslateY(300);
        run.setFont(new Font(20));
        run.setOnAction(e -> {
            if (pAction) {
                pAction = false;
                ranAway = p.run();
                if (ranAway) {
                    console.setText("You successfully ran away");
                    pause.setOnFinished(efe -> {
                        stage.close();
                        try {
                            Stage worldStage = new Stage();
                            WorldScreen worldScreen = new WorldScreen(p);
                            worldScreen.start(worldStage);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                    pause.play();
                } else {
                    enemyAttack(stage);
                }
                updateScreen(stage);
            }
        });

        pause = new PauseTransition();
        pause.setDuration(Duration.seconds(2));

        render = new PauseTransition();
        render.setDuration(Duration.millis(50));

        Pane characters = new Pane(enemySprite, playerSprite);
        Pane information = new Pane(box, hE1.getDisplay(), hP1.getDisplay(), playerHPLabel, enemyHPLabel, attack, run, console);
        VBox root = new VBox(characters, information);
        updateScreen(stage);
        stage.setTitle("CSSE220 Final Project");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void updateScreen(Stage stage) {
        hP1.updateHP((double) p.getHealth() / p.getMAXHP());
        hE1.updateHP((double) enemy.getHealth() / enemy.getMAXHP());
        if (!p.isAlive()) {
            render.setOnFinished(e -> {
                console.setText("Defeat...");
                attack.setVisible(false);
                run.setText("Main Menu");
                run.setDisable(false);
                run.setOnAction(evt -> {
                    stage.close();
                    try {
                        Stage startStage = new Stage();
                        StartScreen startScreen = new StartScreen();
                        startScreen.start(startStage);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            });
            render.play();
        }
        if (!enemy.isAlive()) {
            render.setOnFinished(e -> {
                console.setText("Victory!");
                attack.setVisible(false);
                run.setText("Return to World");
                run.setDisable(false);
                run.setOnAction(evt -> {
                    stage.close();
                    try {
                        SaveFileHelper.save(p);
                        Stage worldStage = new Stage();
                        WorldScreen worldScreen = new WorldScreen(p);
                        worldScreen.start(worldStage);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            });
            render.play();
        }
    }

    private void enemyAttack(Stage stage) {
        animateAttack(enemySprite, playerSprite, -1);
        enemy.attack(p);
        render.setOnFinished(e -> console.setText(enemy.getName() + " damages " + p.getName() + " for " + enemy.getDamage() + " damage!"));
        render.play();
        pause.setOnFinished(e -> {
            pAction = true;
            console.setText("Player's decision");
        });
        pause.play();
        updateScreen(stage);
    }

    private void animateAttack(Node attacker, Node target, double direction) {
        attacker.toFront();

        TranslateTransition lunge = new TranslateTransition(Duration.millis(110), attacker);
        lunge.setFromX(0);
        lunge.setToX(direction * LUNGE_DISTANCE);
        lunge.setInterpolator(Interpolator.EASE_IN);
        lunge.setOnFinished(e -> {
            playHitReaction(target, direction);

            TranslateTransition retreat = new TranslateTransition(Duration.millis(300), attacker);
            retreat.setFromX(direction * LUNGE_DISTANCE);
            retreat.setToX(0);
            retreat.setInterpolator(Interpolator.EASE_BOTH);
            retreat.play();
        });
        lunge.play();
    }

    private void playHitReaction(Node node, double direction) {
        Blend redTint = new Blend(BlendMode.SRC_ATOP);
        redTint.setTopInput(new ColorInput(0, 0, SPRITE_SIZE, SPRITE_SIZE, Color.RED));
        redTint.setOpacity(0.75);
        node.setEffect(redTint);
        new Timeline(
                new KeyFrame(Duration.millis(70), e -> node.setEffect(null)),
                new KeyFrame(Duration.millis(140), e -> node.setEffect(redTint)),
                new KeyFrame(Duration.millis(210), e -> node.setEffect(null))
        ).play();

        TranslateTransition shake = new TranslateTransition(Duration.millis(45), node);
        shake.setFromX(0);
        shake.setToX(direction * 10);
        shake.setCycleCount(6);
        shake.setAutoReverse(true);
        shake.play();
    }

    private String enemySpritePath() {
        switch (enemy.getClass().getSimpleName()) {
            case "Zombie":
                return SPRITE_FOLDER + "zombiesprite.png";
            case "Werewolf":
                return SPRITE_FOLDER + "werewolfsprite.png";
            case "Pirate":
                return SPRITE_FOLDER + "piratesprite.png";
            case "Ogre":
                return SPRITE_FOLDER + "ogresprite.png";
            case "Dragon":
                return SPRITE_FOLDER + "dragonsprite.png";
            case "Death":
                return SPRITE_FOLDER + "deathsprite.png";
            default:
                return SPRITE_FOLDER + "zombiesprite.png";
        }
    }

    private Node loadSprite(String path, Color fallback) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.err.println("Sprite not found: " + path);
            return new Rectangle(SPRITE_SIZE, SPRITE_SIZE, fallback);
        }
        ImageView view = new ImageView(new Image(url.toExternalForm()));
        view.setFitWidth(SPRITE_SIZE);
        view.setFitHeight(SPRITE_SIZE);
        view.setPreserveRatio(true);
        view.setSmooth(false);
        return view;
    }
}