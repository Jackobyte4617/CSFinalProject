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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.net.URL;

public class StartScreen extends Application {
    private TextField nameBox;
    private Label nameBoxLabel;
    //Window Size
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        URL imageUrl = getClass().getResource("/titlescreen1.png");
        if(imageUrl == null){
            System.err.println("Error: couldnt find titlescreen in resources");
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
        title.setStrokeWidth(1.5);

        //Fixing error from merge code, character name input field
        nameBox = new TextField();
        nameBox.setPromptText("Enter your character's name");
        nameBox.setMaxWidth(250);
        nameBox.setStyle("-fx-font-size: 14px;");

        nameBoxLabel = new Label();
        nameBoxLabel.setTextFill(Color.RED);
        nameBoxLabel.setFont(Font.font("System", 14));

        // button to move on to start the game
        Button start = new Button("Start Adventure");
        start.setFont(Font.font(15));
        start.setPrefHeight(50);
        start.setPrefWidth(150);
     //   start.setTranslateX(325 - (start.getBoundsInLocal().getWidth() / 2));
        // start.setTranslateY(400);

        Button load = new Button("Continue Adventure");
        load.setFont(Font.font(15));
        load.setPrefHeight(50);
        load.setPrefWidth(200);
        //load.setTranslateX(300 - (load.getBoundsInLocal().getWidth() / 2));
        //load.setTranslateY(425);
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

                //Stage worldStage = new Stage();
                //stage.close();
                //world.launch(worldStage)
                stage.close();
                Stage worldStage = new Stage();
                WorldScreen worldScreen = new WorldScreen(p);
                worldScreen.start(worldStage);

            }catch (FileNotFoundException e){
                nameBoxLabel.setText("Save file for this character does not exist");
            }catch (Exception e){
                nameBoxLabel.setText("Invalid save file format");
            }
        });

        //button does stuff
        start.setOnAction(event ->{
            if(nameBox.getText().isBlank())
            {
                nameBoxLabel.setText("You must give your character a name");
                return;
            }
            try{
                Player player = new Player(nameBox.getText());
                SaveFileHelper.save(player);
                //Enemy dragondeeznuts = new Dragon();
                stage.close();
                Stage worldStage = new Stage();
                WorldScreen worldScreen = new WorldScreen(player);
                worldScreen.start(worldStage);
            } catch(Exception e){
                e.printStackTrace();
            }
        });

        VBox menuLayout = new VBox(20);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.getChildren().addAll(title,nameBox, nameBoxLabel, start,load);

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
