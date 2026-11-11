package finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class StartScreen extends Application {
    private Label nameBoxLabel;
    private TextField nameBox;
    @Override
    public void start(Stage stage) throws Exception {

        // title
        Text title = new Text("PRESS START 2P");
        title.setFont(Font.font("TIMES NEW ROMAN", 80));
        title.setY(120);
        title.setX(400 - (title.getBoundsInLocal().getWidth() / 2));

        // button to move on to start the game
        Button start = new Button("Start Adventure");
        start.setFont(Font.font(15));
        start.setPrefHeight(50);
        start.setPrefWidth(200);
        start.setTranslateX(300 - (start.getBoundsInLocal().getWidth() / 2));
        start.setTranslateY(350);

        // button to load a save file
        Button load = new Button("Continue Adventure");
        load.setFont(Font.font(15));
        load.setPrefHeight(50);
        load.setPrefWidth(200);
        load.setTranslateX(300 - (load.getBoundsInLocal().getWidth() / 2));
        load.setTranslateY(425);
        load.setOnAction(event ->
            {
                if(nameBox.getText().isBlank())
                {
                    nameBoxLabel.setText("\t You must give your character a name");
                    return;
                }
                try{
                    //WorldScreen world =
                    Player p = SaveFileHelper.loadFile(nameBox.getText());
                    Enemy zombie = new Zombie();
                    stage.close();
                    Stage Display = new Stage();
                    BattleScene d = new BattleScene(p, zombie);
                    d.start(Display);
                    //Stage worldStage = new Stage();
                    //stage.close();
                    //world.launch(worldStage)

                }catch (FileNotFoundException e){
                    nameBoxLabel.setText("\tSave file for this character does not exist");
                }catch (Exception e){
                    nameBoxLabel.setText("\t\t   Invalid save file format");
                }
            });

        nameBoxLabel = new Label("\tPlease enter the name of your character");
        nameBox = new TextField();
        nameBox.setPrefHeight(50);
        nameBox.setPrefWidth(150);
        nameBox.setTranslateX(325 - (start.getBoundsInLocal().getWidth() / 2));
        nameBox.setTranslateY(250);
        nameBoxLabel.setPrefHeight(50);
        nameBoxLabel.setPrefWidth(250);
        nameBoxLabel.setTranslateX(275 - (start.getBoundsInLocal().getWidth() / 2));
        nameBoxLabel.setTranslateY(200);



        //button does stuff
        start.setOnAction(event ->{
            if(nameBox.getText().isBlank())
            {
                nameBoxLabel.setText("\t You must give your character a name");
                return;
            }
            try{
                Player player = new Player(nameBox.getText());
                Enemy zombie = new Zombie();
                stage.close();
                Stage Display = new Stage();
                BattleScene d = new BattleScene(player, zombie);
                d.start(Display);
            } catch(Exception e){
                e.printStackTrace();
            }
        });

        // root and stuff
        Pane root = new Pane(title, start, load,nameBox,nameBoxLabel);
        stage.setTitle("Title Screen");
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
