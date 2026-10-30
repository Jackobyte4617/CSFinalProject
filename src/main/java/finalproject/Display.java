package finalproject;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Display extends Application {
    @Override
    public void start(Stage stage)
    {

        Rectangle e1 = new Rectangle(130,40,30,60);
        Rectangle e2 = new Rectangle(80,130,30,60);
        Rectangle e3 = new Rectangle(130,220,30,60);
        e1.setFill(new Color(.9,.3,.2,1));
        e2.setFill(new Color(.9,.3,.2,1));
        e3.setFill(new Color(.9,.3,.2,1));
        Rectangle p1 = new Rectangle(640,40,30,60);
        Rectangle p2 = new Rectangle(690,130,30,60);
        Rectangle p3 = new Rectangle(640,220,30,60);
        p1.setFill(new Color(.2,.3,.9,1));
        p2.setFill(new Color(.2,.3,.9,1));
        p3.setFill(new Color(.2,.3,.9,1));
        Rectangle box = new Rectangle(0,40,800,280);
        HealthBar hE1 = new HealthBar(40,78);
        HealthBar hE2 = new HealthBar(40,168);
        HealthBar hE3 = new HealthBar(40,258);
        HealthBar hP1 = new HealthBar(240,78);
        HealthBar hP2 = new HealthBar(240,168);
        HealthBar hP3 = new HealthBar(240,258);
        Pane characters = new Pane(e1, e2,e3,p1,p2,p3);
        Pane information = new Pane(box,hE1.getDisplay(),hE2.getDisplay(),hE3.getDisplay(),hP1.getDisplay(),hP2.getDisplay(),hP3.getDisplay());
        VBox root = new VBox(characters,information);
        stage.setTitle("CSSE220 Final Project");
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
