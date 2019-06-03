import java.awt.Color;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Main{

    //Main
    public static Main main = new Main();

    //Window, Frame, Panel
    private JFrame mainFrame = new JFrame("Snake");
    public JPanel mainPanel = new JPanel();

    //Frame and Panel variables
    private int gameWidth = 400;
    private int gameHeight = 400;

    //Game running bool
    private boolean gameRunning = true;

    //Game speed - higher the slower the game
    private int gameSpeed = 75;

    //Snakehead
    private ImageIcon head = new ImageIcon("head.png");
    private JLabel snakehead = new JLabel(head);

    //Snaketail
    private ImageIcon dot = new ImageIcon("dot.png");
    private int snakeLength = 0;

    //Apple
    private ImageIcon app = new ImageIcon("apple.png");
    private JLabel apple = new JLabel(app);

    //Snakehead directions - starting towards left
    private int verticalDirection = 0; // -10 = upwards, 10 = downwards
    private int horizontalDirection = 10; // -10 = left, 10 = right

    //Snakehead location
    private int snakeheadLocation_x[] = new int[200];
    private int snakeheadLocation_y[] = new int[200];

    //Snaketail arraylist
    private ArrayList<JLabel> snaketail_list = new ArrayList<JLabel>();

    //Apple location
    private int appleLocation_x;
    private int appleLocation_y;
    private Random rand = new Random();

    //Frame and Panel setup
    private void initialize_frame_and_panel(){
        mainFrame.setSize(gameWidth, gameHeight);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.setBounds(0, 0, gameWidth, gameHeight);
        mainPanel.setVisible(true);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();

        mainFrame.add(mainPanel);
    }

    //Initialize componenets - snakehead and apple
    private void initialize_components(){
        //Snakehead
        snakeheadLocation_x[0] = gameWidth/2;
        snakeheadLocation_y[0] = gameHeight/2;
        snakehead.setBounds(snakeheadLocation_x[0], snakeheadLocation_y[0], 10, 10);
        mainPanel.add(snakehead);

        //Apple
        appleLocation_x = rand.nextInt(gameWidth / 10) * 10;
        appleLocation_y = rand.nextInt(gameHeight / 10) * 10;
        apple.setBounds(appleLocation_x, appleLocation_y, 10, 10);
        mainPanel.add(apple);
    }

    //Randomize apple location
    private void randomize_apple_location(){
        appleLocation_x = rand.nextInt(gameWidth / 10) * 10;
        appleLocation_y = rand.nextInt(gameHeight / 10) * 10;
        apple.setBounds(appleLocation_x, appleLocation_y, 10, 10);
        mainPanel.repaint();
    }

    //Refresh snakehead
    private void refresh_snakehead(){
        snakehead.setBounds(snakeheadLocation_x[0], snakeheadLocation_y[0], 10, 10);
        mainPanel.repaint();
    }

    //Create snaketail
    private void add_snaketail(){
        JLabel snaketail = new JLabel(dot);
        snaketail.setBounds(snakeheadLocation_x[snakeLength], snakeheadLocation_y[snakeLength], 10 , 10);
        mainPanel.add(snaketail);
        snaketail_list.add(snaketail);

        mainPanel.repaint();
    }

    private void refresh_snaketail(){
        for (int i = 0; i < snakeLength; i++){;
            snaketail_list.get(i).setBounds(snakeheadLocation_x[i + 1], snakeheadLocation_y[i + 1], 10, 10);
            mainPanel.repaint();
        }
    }

    //Collision with apple check
    private void appleCollision(){
        if (appleLocation_x == snakeheadLocation_x[0] && appleLocation_y == snakeheadLocation_y[0]){
            randomize_apple_location();
            snakeLength = snakeLength + 1;
            add_snaketail();
        }
    }

    //Self collision check
    private void selfCollision(){
        for (int i = 1; i < snakeLength; i++){
            if (snakeheadLocation_x[0] == snakeheadLocation_x[i] && snakeheadLocation_y[0] == snakeheadLocation_y[i]){
                gameRunning = false;
            }
        }
    }

    //Offscreen check
    private void offScreenCheck(){
        if(snakeheadLocation_x[0] >= gameWidth){
            snakeheadLocation_x[0] = 0;
        }
        else if(snakeheadLocation_x[0] <= 0){
            snakeheadLocation_x[0] = gameWidth;
        }
        
        if(snakeheadLocation_y[0] >= gameHeight){
            snakeheadLocation_y[0] = 0;
        }
        else if(snakeheadLocation_y[0] <= 0){
            snakeheadLocation_y[0] = gameHeight;
        }
    }

    //Game loop
    private void gameloop(){
        
        while(gameRunning){
            try{
                Thread.sleep(gameSpeed);

                //Shift previous location by one to the right on the array
                /*
                Snake length = 5;

                0 1 2 3 4
                [][][][][]

                3 -> 4, 2 -> 3, 1-> 2, 0 -> 1
                */
                for (int i = snakeLength; i > 0; i--){
                    snakeheadLocation_x[i] = snakeheadLocation_x[i - 1];
                    snakeheadLocation_y[i] = snakeheadLocation_y[i - 1];
                }

                //Move snakehead by one direction
                snakeheadLocation_x[0] = snakeheadLocation_x[0] + horizontalDirection;
                snakeheadLocation_y[0] = snakeheadLocation_y[0] + verticalDirection;
                refresh_snakehead();

                //Check for collision, apples, direction change, store location etc
                appleCollision();
                selfCollision();
                offScreenCheck();

                //Refresh snaketail - if there is one
                if (snaketail_list.size() > 0){
                    refresh_snaketail();
                }

            }catch(InterruptedException Ie){
                System.out.println("InterruptedException with gameLoop");
            }
        }
    }

    //Direction change keylistener
    private void KeyListener(){
        mainPanel.addKeyListener(new KeyListener(){
            public void keyTyped(KeyEvent e){}
            public void keyReleased(KeyEvent e){}
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    horizontalDirection = 10;
                    verticalDirection = 0;
                }
                else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    horizontalDirection = -10;
                    verticalDirection = 0;
                }
                else if(e.getKeyCode() == KeyEvent.VK_UP){
                    verticalDirection = -10;
                    horizontalDirection = 0;
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    verticalDirection = 10;
                    horizontalDirection = 0;
                }
            }
        });
    }

    //Endgame Screen
    private void endgameScreen(){
        mainPanel.removeAll();
        
        JLabel gameOver_Label = new JLabel("GAME OVER");
        gameOver_Label.setBounds(gameWidth/4, gameHeight/4, 200, 200);
        gameOver_Label.setForeground(Color.GREEN);
        mainPanel.add(gameOver_Label);
        mainPanel.repaint();
    }

    public static void main(String[] args){
        main.initialize_frame_and_panel();
        main.initialize_components();
        main.KeyListener();
        main.gameloop();
        main.endgameScreen();
    }

}