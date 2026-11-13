package finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class SaveFileHelper {
    /*
    Save file format
    hp
    //world position hp
    //May need to exist depending on map implementation
    //Enemies dead bit sequence in reverse order
    //110110
     */

    //will build and then return WorldScreen
    public static Player loadFile(String fileName) throws FileNotFoundException {
        File file = new File("src/main/java/SaveFiles/"+fileName+".txt");
        Scanner reader = new Scanner(file);
        //int world = Integer.parseInt(reader.next());
        //int pos = Integer.parseInt(reader.next());
        int hP = Integer.parseInt(reader.next());
        //reader.nextLine();
        //May need to exist depending on map implementation
        /*int enemiesAliveBits = Integer.parseInt(reader.nextLine());
        boolean[] enemiesAlive = new boolean[6];
        for(int i=0;i<6;i++){
            if(enemiesAliveBits%10==0)enemiesAlive[i]=false;
            else if(enemiesAliveBits%10==1)enemiesAlive[i]=true;
            else throw new InvalidParameterException();
            enemiesAliveBits/=10;
            }
         */
        reader.close();
        if(hP<=0)throw new InvalidParameterException();
        //temporary
        Player p = new Player(fileName);
        p.takeDamage(p.getHealth()-hP);
        return p;
        //WorldScreen worldScreen = new WorldScreen();
        //worldScreen.getPlayer().takeDamage(world.getPlayer().health-hp);
        //updating position and world to saved values, code depends on implementation
        //return worldScreen
    }
    public static void save(Player p) throws IOException {
        File file = new File("src/main/java/SaveFiles/"+p.getName()+".txt");
        file.delete();
        FileWriter writer = new FileWriter("src/main/java/SaveFiles/"+p.getName()+".txt");
        writer.write(p.getHealth()+"");
        writer.close();
    }
}
