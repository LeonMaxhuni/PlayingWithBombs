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
        Keybindings myBinds = new Keybindings();

        Player player1 = new Player(14, 12, 0, 1, myBinds.binds1);
        Player player2 = new Player(14, 12, 1, 2);
        Player allPlayers[] = {player1,player2};

        Bomb bomb1 = new Bomb(player1.color, 0);
        Bomb bomb2 = new Bomb(player1.color, 1);
        Bomb bomb3 = new Bomb(player1.color, 2);
        Bomb bomb4 = new Bomb(player1.color, 3);
        Bomb bomb5 = new Bomb(player1.color, 4);
        Bomb bomb6 = new Bomb(player1.color, 5);
        Bomb player1Bombs[] = {bomb1, bomb2, bomb3, bomb4, bomb5, bomb6};

        Objects myObjects = new Objects(allPlayers);

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

        Labels countLab2[] =
        {
            new Labels(0, 192, 64, 64, 1, 0),
            new Labels(0, 256, 64, 64, 2, 0),
            new Labels(0, 320, 64, 64, 3, 0)
        };

        Labels rangeLab2[] =
        {
            new Labels(64, 192, 64, 64, 1, 1),
            new Labels(64, 256, 64, 64, 2, 1),
            new Labels(64, 320, 64, 64, 3, 1)
        };

        Labels speedLab2[] =
        {
            new Labels(128, 192, 64, 64, 1, 2),
            new Labels(128, 256, 64, 64, 2, 2),
            new Labels(128, 320, 64, 64, 3, 2),
        };

        Labels countLab3[] =
        {
            new Labels(0, 384, 64, 64, 1, 0),
            new Labels(0, 448, 64, 64, 2, 0),
            new Labels(0, 512, 64, 64, 3, 0)
        };

        Labels rangeLab3[] =
        {
            new Labels(64, 384, 64, 64, 1, 1),
            new Labels(64, 448, 64, 64, 2, 1),
            new Labels(64, 512, 64, 64, 3, 1)
        };

        Labels speedLab3[] =
        {
            new Labels(128, 384, 64, 64, 1, 2),
            new Labels(128, 448, 64, 64, 2, 2),
            new Labels(128, 512, 64, 64, 3, 2),
        };

        Labels countLab4[] =
        {
            new Labels(0, 576, 64, 64, 1, 0),
            new Labels(0, 640, 64, 64, 2, 0),
            new Labels(0, 704, 64, 64, 3, 0)
        };

        Labels rangeLab4[] =
        {
            new Labels(64, 576, 64, 64, 1, 1),
            new Labels(64, 640, 64, 64, 2, 1),
            new Labels(64, 704, 64, 64, 3, 1)
        };

        Labels speedLab4[] =
        {
            new Labels(128, 576, 64, 64, 1, 2),
            new Labels(128, 640, 64, 64, 2, 2),
            new Labels(128, 704, 64, 64, 3, 2),
        };

        Labels player1Labels[][] = {countLab1, rangeLab1, speedLab1};
        Labels player2Labels[][] = {countLab2, rangeLab2, speedLab2};
        Labels player3Labels[][] = {countLab3, rangeLab3, speedLab3};
        Labels player4Labels[][] = {countLab4, rangeLab4, speedLab4};

        Labels allPlayersPowerLabels[][][] = {player1Labels, player2Labels, player3Labels, player4Labels};

        GamePanel gamePanel = new GamePanel(allPlayers, player1Bombs, myObjects ,myMap, myBinds);
        ScorePanel scorePanel = new ScorePanel(allPlayers, player1Bombs, allPlayersPowerLabels);

        MainPanel mainPanel = new MainPanel(gamePanel, scorePanel, allPlayers, player1Bombs);

        myFrame.add(mainPanel);

        myFrame.pack();
        myFrame.setVisible(true);
        mainPanel.startMyThread();
    }
}