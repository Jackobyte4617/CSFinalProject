package finalproject;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class HealthBar {
    private Group bar;
    private Rectangle hP;
    private Text hPText;
    private Creature creature;

    //public HealthBar(int x, int y,Creature c)
    public HealthBar(int x, int y, Creature c)
    {
        Rectangle box = new javafx.scene.shape.Rectangle(124, 24, new javafx.scene.paint.Color(.7, .7, .7, 1));
        hP = new javafx.scene.shape.Rectangle(2, 2, 120, 20);
        hP.setFill(new Color(1, 0, 0, 1));
        this.creature = c;
        hPText = new Text(5,17.5,creature.getMAXHP() + "/" + creature.getMAXHP());
        hPText.setFont(new Font(15));
        bar = new Group(box,hP,hPText);
        bar.setTranslateX(x);
        bar.setTranslateY(y);
        //update();
    }

    //public void update()
    //    {
    //        hP.setWidth(120*creature.getMaxHP/(double)creature.getHP);
    //        hp.setText(creture.getHP()+"/"+creature.getMaxHP());
    //    }
    public void updateHP(double hPPercent)
    {
        System.out.println(hPPercent);
        hP.setWidth(120*hPPercent);
        hPText.setText((int)(creature.getMAXHP()*hPPercent+0.01)+ "/" + creature.getMAXHP());
    }
    public Group getDisplay()
    {
        return bar;
    }
}
