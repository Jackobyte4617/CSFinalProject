package finalproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

public class StartScreen extends Application {
    //Window Size
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        URL imageUrl = getClass().getResource("/titlescreen1.png");
        if(imageUrl == null){
            System.err.println("Error: uldnt find titlescreen in resources");
        } else{
            Image backgroundImage = new Image(imageUrl.toExternalForm());
            ImageView backgroundView = new ImageView(backgroundImage);

            backgroundView.fitWidthProperty().bind(stage.widthProperty());
            backgroundView.fitHeightProperty().bind(stage.heightProperty());

            root.getChildren().add(backgroundView);
        }

        // title
        Text title = new Text("PRESS START 2P");
        title.setFont(Font.font("TIMES NEW ROMAN", 80));
       //old set cords i think
        // title.setY(120);
       // title.setX(400 - (title.getBoundsInLocal().getWidth() / 2));
        title.setFill(Color.WHITE);
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(1.5);

        // button to move on to start the game
        Button start = new Button("Start Adventure");
        start.setFont(Font.font(15));
        start.setPrefHeight(50);
        start.setPrefWidth(150);
     //   start.setTranslateX(325 - (start.getBoundsInLocal().getWidth() / 2));
        // start.setTranslateY(400);

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
                BattleScene d = new BattleScene(player, dragondeeznuts);
                d.start(Display);
            } catch(Exception e){
                e.printStackTrace();
            }
        });

        VBox menuLayout = new VBox(40);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getChildren().addAll(title,start);

        // root and stuff
        root.getChildren().add(menuLayout);
        stage.setTitle("Title Screen");
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
