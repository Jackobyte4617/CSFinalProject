package finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class StartScreen extends Application {
    private Label fileNameBoxLabel;
    private TextField fileNameBox;
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
        start.setPrefWidth(150);
        start.setTranslateX(325 - (start.getBoundsInLocal().getWidth() / 2));
        start.setTranslateY(200);

        // button to load a save file
        Button load = new Button("Continue Adventure");
        load.setFont(Font.font(15));
        load.setPrefHeight(50);
        load.setPrefWidth(200);
        load.setTranslateX(300 - (load.getBoundsInLocal().getWidth() / 2));
        load.setTranslateY(425);
        load.setOnAction(event ->
            {
                try{
                    //WorldScreen world =
                    SaveFileHelper.loadFile(fileNameBox.getText());
                    //Stage worldStage = new Stage();
                    //stage.close();
                    //world.launch(worldStage)

                }catch (FileNotFoundException e){
                    fileNameBoxLabel.setText("\t     Entered file could not be found");
                }catch (Exception e){
                    fileNameBoxLabel.setText("\t\t\tInvalid file format");
                }
            });

        fileNameBoxLabel = new Label("   Please enter the name of your save file below\n\t\tto continue your adventure");
        fileNameBox = new TextField();
        fileNameBox.setPrefHeight(50);
        fileNameBox.setPrefWidth(150);
        fileNameBox.setTranslateX(325 - (start.getBoundsInLocal().getWidth() / 2));
        fileNameBox.setTranslateY(350);
        fileNameBoxLabel.setPrefHeight(50);
        fileNameBoxLabel.setPrefWidth(250);
        fileNameBoxLabel.setTranslateX(275 - (start.getBoundsInLocal().getWidth() / 2));
        fileNameBoxLabel.setTranslateY(300);

        // root and stuff
        Pane root = new Pane(title, start, load,fileNameBox,fileNameBoxLabel);
        stage.setTitle("Title Screen");
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }
}
