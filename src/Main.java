import java.io.IOException;

import javax.swing.*;

public class Main extends JFrame
{

    public static void main(String[] args) throws IOException
    {

        JFrame myFrame = new JFrame();

        myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        myFrame.setResizable(false);
        myFrame.setLocationRelativeTo(null);
        myFrame.setTitle("PlayingWithBombs");

        ImageIcon gameIcon = new ImageIcon("textures//differentstatic3.png");
        myFrame.setIconImage(gameIcon.getImage());

        Map myMap = new Map();

        Player player1 = new Player(13, 2, 0, 1);
        Player player2 = new Player(10, 10, 1, 2);
        Player allPlayers[] = {player1,player2};

        Bomb bomb1 = new Bomb(player1.color, 0);
        Bomb bomb2 = new Bomb(player1.color, 1);
        Bomb bomb3 = new Bomb(player1.color, 2);
        Bomb player1Bombs[] = {bomb1, bomb2, bomb3};

        Labels countLab1[] =
        {
            new Labels(0, 0, 64, 64, 1, 0),
            new Labels(0, 64, 64, 64, 2, 0),
            new Labels(0, 128, 64, 64, 3, 0)
        };

        Labels rangeLab1[] =
        {
            new Labels(64, 0, 64, 64, 1, 1),
            new Labels(64, 64, 64, 64, 2, 1),
            new Labels(64, 128, 64, 64, 3, 1)
        };

        Labels speedLab1[] =
        {
            new Labels(128, 0, 64, 64, 1, 2),
            new Labels(128, 64, 64, 64, 2, 2),
            new Labels(128, 128, 64, 64, 3, 2),
        };

        Labels player1Labels[][] = {countLab1, rangeLab1, speedLab1};

        //Labels footLabel = new Labels(64, 64, 0);
        /*JLabel picLabel1 = new JLabel();
        picLabel1.setIcon(gameIcon);
        picLabel1.setPreferredSize(new Dimension(64, 64));

        JLabel picLabel2 = new JLabel();
        picLabel2.setIcon(gameIcon);
        picLabel2.setPreferredSize(new Dimension(64, 64));

        JLabel picLabel3 = new JLabel();
        picLabel3.setIcon(gameIcon);
        picLabel3.setPreferredSize(new Dimension(64, 64));
        //picLabel.setBounds(0, 0, 64, 64);;
        
        //picLabel.setHorizontalAlignment(JLabel.RIGHT);
        //picLabel.setVerticalAlignment(JLabel.CENTER);
        //picLabel.setBackground(Color.CYAN);
        //picLabel.setOpaque(true);
        
        //picLabel.setVisible(true);
        */

        GamePanel gamePanel = new GamePanel(allPlayers, player1Bombs, myMap);
        ScorePanel scorePanel = new ScorePanel(allPlayers, player1Bombs, player1Labels);

        MainPanel mainPanel = new MainPanel(gamePanel, scorePanel, allPlayers, player1Bombs);

        myFrame.add(mainPanel);

        myFrame.pack();
        myFrame.setVisible(true);
        mainPanel.startMyThread();
    }
}