package finalproject;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.util.Duration;


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

    public BattleScene(Player p, Enemy e){
        this.p = p;
        this.enemy = e;
    }

    @Override
    public void start(Stage stage)
    {
        Rectangle e1 = new Rectangle(130, 80, 60, 120);
        //Rectangle e2 = new Rectangle(80,130,30,60);
        //Rectangle e3 = new Rectangle(130,220,30,60);
        e1.setFill(new Color(.9,.3,.2,1));
        //e2.setFill(new Color(.9,.3,.2,1));
        //e3.setFill(new Color(.9,.3,.2,1));

        Rectangle p1 = new Rectangle(640, 80, 60, 120);
        //Rectangle p2 = new Rectangle(690,130,30,60);
        //Rectangle p3 = new Rectangle(640,220,30,60);
        p1.setFill(new Color(.2,.3,.9,1));
        //p2.setFill(new Color(.2,.3,.9,1));
        //p3.setFill(new Color(.2,.3,.9,1));

        Rectangle box = new Rectangle(0, 80, 800, 320);


        hE1 = new HealthBar(40,128, enemy);
        //HealthBar hE2 = new HealthBar(40,168);
        //HealthBar hE3 = new HealthBar(40,258);

        hP1 = new HealthBar(40,328, p);
        //HealthBar hP2 = new HealthBar(240,168);
        //HealthBar hP3 = new HealthBar(240,258);

        Text playerHPLabel = new Text(60, 310, "Player");
        playerHPLabel.setFont(new Font(20));
        playerHPLabel.setFill(new Color(0.8,0.8,0.8,1));

        Text enemyHPLabel = new Text(60, 110, "Enemy");
        enemyHPLabel.setFont(new Font(20));
        enemyHPLabel.setFill(new Color(0.8,0.8,0.8,1));

        console = new Text(180, 210, "An " + enemy.getName() + " is attacking you, start battle!");
        console.setFont(new Font(20));
        console.setFill(new Color(0.8, 0.8, 0.8, 1));

        Button attack = new Button("Attack");
        attack.setTranslateX(600);
        attack.setTranslateY(100);
        attack.setFont(new Font(20));
        attack.setOnAction(e -> {
            if(pAction) {
                pAction = false;
                p.attack(enemy);
                updateScreen(stage);
                console.setText(p.getName() + " damages " + enemy.getName() + " for " + p.getDamage());
                pause.setOnFinished(efe -> {
                    if(enemy.isAlive()){
                        enemyAttack(stage);
                    }
                });
                pause.play();
            }
        });

        Button run = new Button("RUN!");
        run.setTranslateX(600);
        run.setTranslateY(300);
        run.setFont(new Font(20));
        run.setOnAction(e -> {
            if(pAction){
                pAction = false;
                ranAway = p.run();
                if(ranAway){
                    console.setText("You successfully ran away");
                    pause.setOnFinished(efe -> {
                        stage.close();
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

        Pane characters = new Pane(e1, p1);
        Pane information = new Pane(box,hE1.getDisplay(),hP1.getDisplay(), playerHPLabel, enemyHPLabel, attack, run, console);
        VBox root = new VBox(characters,information);
        updateScreen(stage);
        stage.setTitle("CSSE220 Final Project");
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);

    }

    public void updateScreen(Stage stage){
        hP1.updateHP((double) p.getHealth() / p.getMAXHP());
        hE1.updateHP((double) enemy.getHealth() / enemy.getMAXHP());
        if(!p.isAlive()) {
            render.setOnFinished(e -> console.setText("Defeat..."));
            pause.setOnFinished(e -> {
                stage.close();
            });
            render.play();
            pause.play();
        }
        if(!enemy.isAlive()){
            render.setOnFinished(e -> console.setText("Victory!"));
            pause.setOnFinished(e -> {
                stage.close();
            });
            render.play();
            pause.play();
        }
    }

    private void enemyAttack(Stage stage) {
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
}
