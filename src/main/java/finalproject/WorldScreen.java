package finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class WorldScreen extends Application {
    private Player player;
    public WorldScreen(Player player){
        this.player = player;
    }
    public WorldScreen(){
        this.player = new Player("Adventure");
    }
    @Override
    public void start(Stage stage) throws Exception {

        // title
        Text title = new Text("World map- choose enemy");
        title.setFont(Font.font("TIMES NEW ROMAN", 40));
       // title.setY(120);
        //title.setX(400 - (title.getBoundsInLocal().getWidth() / 2));
        Text scoreText = new Text("Enemies Defeated: " + player.getEnemiesDefeated());
        scoreText.setFont(Font.font(18));
        // ADDED: Buttons to select different opponents
        Button zombieBtn = new Button("Fight Zombie");
        Button ogreBtn = new Button("Fight Ogre");
        Button dragonBtn = new Button("Fight Dragon");
        Button werewolfBtn = new Button("Fight Werewolf");
        Button pirateBtn = new Button("Fight Pirate");
        Button deathBtn = new Button("Fight Death");

        styleButton(zombieBtn);
        styleButton(ogreBtn);
        styleButton(dragonBtn);
        styleButton(werewolfBtn);
        styleButton(pirateBtn);
        styleButton(deathBtn);

        // ADDED: Action handlers to launch BattleScene with the chosen enemy
        zombieBtn.setOnAction(event -> startBattle(stage, new Zombie()));
        ogreBtn.setOnAction(event -> startBattle(stage, new Ogre()));
        dragonBtn.setOnAction(event -> startBattle(stage, new Dragon()));
        werewolfBtn.setOnAction(event -> startBattle(stage, new Werewolf()));
        pirateBtn.setOnAction(event -> startBattle(stage, new Pirate()));
        deathBtn.setOnAction(event -> startBattle(stage, new Death()));

        // CHANGED: VBox layout to display title and enemy choice buttons
        VBox root = new VBox(20, title,scoreText, zombieBtn, ogreBtn, dragonBtn, werewolfBtn, pirateBtn, deathBtn);
        root.setAlignment(Pos.CENTER);

        stage.setTitle("World Screen");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    // ADDED: Helper method to launch BattleScene passing player and enemy
    private void startBattle(Stage stage, Enemy enemy) {
        try {
            stage.close();
            Stage battleStage = new Stage();
            BattleScene battleScene = new BattleScene(player, enemy);
            battleScene.start(battleStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ADDED: Helper method for button styling
    private void styleButton(Button btn) {
        btn.setFont(Font.font(15));
        btn.setPrefHeight(40);
        btn.setPrefWidth(200);
    }
    public static void main(String[] args){
        launch(args);
    }
}
