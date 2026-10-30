package finalproject;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class HealthBar {
    private Group bar;
    private Rectangle hP;

    public HealthBar(int x, int y)
    {
        Rectangle box = new javafx.scene.shape.Rectangle(124, 24, new javafx.scene.paint.Color(.7, .7, .7, 1));
        hP = new javafx.scene.shape.Rectangle(2, 2, 120, 20);
        hP.setFill(new Color(1, 0, 0, 1));
        bar = new Group(box,hP);
        bar.setTranslateX(x);
        bar.setTranslateY(y);
    }
    public void updateHP(double hPPercent)
    {
        hP.setWidth(120*hPPercent);
    }
    public Group getDisplay()
    {
        return bar;
    }
}
