import java.util.*;
import javax.swing.*;

public class Main{

    //Main
    public static Main main = new Main();

    //Window, Frame, Panel
    private JFrame mainFrame = new JFrame("Snake");
    public JPanel mainPanel = new JPanel();

    //Frame and Panel variables
    private int gameWidth = 800;
    private int gameHeight = 800;

    //Game running bool
    private boolean gameRunning = true;

    //Snakehead
    private Snakehead snakehead = new Snakehead(400, 400);

    //Snakehead directions
    private int verticalDirection = 10; // -10 = upwards, 10 = downwards
    private int horizontalDirection = 10; // -10 = left, 10 = right

    //Snakehead location
    private int snakeheadLocation_x[];
    private int snakeheadLocation_y[];

    //Frame and Panel setup
    private void initialize_frame_and_panel(){
        mainFrame.setSize(gameWidth, gameHeight);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.setBounds(0, 0, gameWidth, gameHeight);
        mainPanel.setOpaque(false);

        mainFrame.add(mainPanel);
    }

    //Game loop
    private void gameloop(){
        while(gameRunning){
            try{
                Thread.sleep(1000);
                //Move snakehead by one direction
                snakeheadLocation_x[0] = snakeheadLocation_x[0] + horizontalDirection;
                snakeheadLocation_y[0] = snakeheadLocation_x[0] + verticalDirection;

                snakehead.set_Snakehead_position(snakeheadLocation_x[0], snakeheadLocation_y[0]);

                //Check for collision, apples etc
            }catch(InterruptedException Ie){
                System.out.println("InterruptedException with gameLoop");
            }
        }
    }

    public static void main(String[] args){
        main.initialize_frame_and_panel();
        main.gameloop();
    }

}