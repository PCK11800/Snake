import java.util.*;
import javax.swing.*;

public class Snakehead extends Main{

    private int x_location;
    private int y_location;

    //ImageIcon
    private ImageIcon head = new ImageIcon("head.png");
    private JLabel Snakehead = new JLabel(head);

    //Constructor that adds snakehead to board
    public Snakehead(int x, int y){
        x_location = x;
        y_location = y;
        Snakehead.setBounds(x_location, y_location, 10, 10);
        mainPanel.add(Snakehead);
    }

    //Return x and y location
    public int return_Snakehead_x_position(){
        return x_location;
    }

    public int return_Snakehead_y_position(){
        return y_location;
    }

    //Set x and y location
    public void set_Snakehead_x_position(int x){
        x_location = x;
        Snakehead.setBounds(x_location, y_location, 10, 10);
        mainPanel.repaint();
    }

    public void set_Snakehead_y_position(int y){
        y_location = y;
        Snakehead.setBounds(x_location, y_location, 10, 10);
        mainPanel.repaint();
    }

    //Set both location
    public void set_Snakehead_position(int x, int y){
        x_location = x;
        y_location = y;
        Snakehead.setBounds(x_location, y_location, 10, 10);
        mainPanel.repaint();
    }

    //Refresh position
    public void refresh_Snakehead(){
        Snakehead.setBounds(x_location, y_location, 10, 10);
        mainPanel.repaint();
    }
}