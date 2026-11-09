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

public class WorldScreen extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        // title
        Text title = new Text("World button 1");
        title.setFont(Font.font("TIMES NEW ROMAN", 80));
        title.setY(120);
        title.setX(400 - (title.getBoundsInLocal().getWidth() / 2));

        // button to move on to start the game
        Button start = new Button("Fight?");
        start.setFont(Font.font(15));
        start.setPrefHeight(50);
        start.setPrefWidth(150);
        start.setTranslateX(325 - (start.getBoundsInLocal().getWidth() / 2));
        start.setTranslateY(400);

        //button does stuff
        start.setOnAction(event ->{
            try{
                stage.close();
                Stage worldStage = new Stage();
                StarterApp mainGameApp = new StarterApp();
                mainGameApp.start(worldStage);
            } catch(Exception e){
                e.printStackTrace();
            }
        });

        // root and stuff
        Pane root = new Pane(title, start);
        stage.setTitle("World Screen");
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
