import java.util.*;
import javax.swing.*;

public class Apple extends Main{

    private int x_location;
    private int y_location;

    //ImageIcon
    private ImageIcon apple = new ImageIcon("apple.png");
    private JLabel Apple = new JLabel(apple);

    //Constructor that adds Apple to board
    public Apple(int x, int y){
        x_location = x;
        y_location = y;
        mainPanel.add(Apple);
    }

    //Return x and y location
    public int return_Apple_x_position(){
        return x_location;
    }

    public int return_Apple_y_position(){
        return y_location;
    }

    //Set x and y location
    public void set_Apple_x_position(int x){
        x_location = x;
        mainPanel.repaint();
    }

    public void set_Apple_y_position(int y){
        y_location = y;
        mainPanel.repaint();
    }

    //Refresh position
    public void refresh_Apple(){
        mainPanel.repaint();
    }
}