import java.util.*;
import javax.swing.*;

public class Snakebody extends Main{

    private int x_location;
    private int y_location;

    //ImageIcon
    private ImageIcon dot = new ImageIcon("dot.png");
    private JLabel Snakebody = new JLabel(dot);

    //Constructor that adds Snakebody to board
    public Snakebody(int x, int y){
        x_location = x;
        y_location = y;
        mainPanel.add(Snakebody);
    }

    //Return x and y location
    public int return_Snakebody_x_position(){
        return x_location;
    }

    public int return_Snakebody_y_position(){
        return y_location;
    }

    //Set x and y location
    public void set_Snakebody_x_position(int x){
        x_location = x;
        mainPanel.repaint();
    }

    public void set_Snakebody_y_position(int y){
        y_location = y;
        mainPanel.repaint();
    }

    //Refresh position
    public void refresh_Snakebody(){
        mainPanel.repaint();
    }
}