import javax.swing.*;

public class Main extends JFrame
{

    public static void main(String[] args)
    {

        JFrame myFrame = new JFrame();

        myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        myFrame.setResizable(false);
        myFrame.setLocationRelativeTo(null);
        myFrame.setTitle("PlayingWithBombs");
        //myFrame.setLayout(null);

        //myFrame.setSize(1160, 832);

        ImageIcon gameIcon = new ImageIcon("textures//differentstatuc3.png");
        myFrame.setIconImage(gameIcon.getImage());

        /*
        GamePanel myPanel = new GamePanel();
        ScorePanel scPanel = new ScorePanel();
        myFrame.add(myPanel);
        myFrame.add(scPanel);
        */

        Player player1 = new Player(2, 2, 0, 1);
        Player player2 = new Player(10, 10, 1, 2);
        Player allPlayers[] = {player1,player2};

        Bomb bomb1 = new Bomb(player1.color, 0);
        Bomb bomb2 = new Bomb(player1.color, 1);
        Bomb bomb3 = new Bomb(player1.color, 2);
        Bomb player1Bombs[] = {bomb1, bomb2, bomb3};

        GamePanel gamePanel = new GamePanel(allPlayers, player1Bombs);
        ScorePanel scorePanel = new ScorePanel(allPlayers, player1Bombs);

        MainPanel mainPanel = new MainPanel(gamePanel, scorePanel);
        //GamePanel gamePanel = new GamePanel();
        //mainPanel.add(gamePanel);
        //mainPanel.add(new ScorePanel());
        

        myFrame.add(mainPanel);

        myFrame.pack();
        myFrame.setVisible(true);
        mainPanel.startMyThread();
    }
}