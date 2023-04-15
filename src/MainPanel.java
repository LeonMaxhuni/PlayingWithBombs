import java.awt.Color;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class MainPanel extends JPanel implements Runnable
{    
    final short frameWidth = 1280;
    final short frameHeight = 832;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;

    short FPS = 60;
    Thread myThread;

    Bomb player1Bombs[];
    Bomb player2Bombs[];
    Player players[];

    public MainPanel(JPanel panel1, JPanel panel2, Player players[], Bomb player1Bombs[])
    {
        this.setPreferredSize(new DimensionUIResource(frameWidth,frameHeight));
        this.setBackground(Color.YELLOW);
        this.setVisible(true);
        this.setLayout(null);
        gamePanel = (GamePanel)panel1;
        scorePanel = (ScorePanel)panel2;
        this.add(panel1);
        this.add(panel2);
        this.players = players;
        this.player1Bombs = player1Bombs;
    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long reachOne = 0;
        //int showFPS = 0;

        while(myThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            reachOne += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1)
            {
                //gamePanel.update(players, player1Bombs);
                gamePanel.update();
                scorePanel.update();
                gamePanel.repaint();
                scorePanel.repaint();
                delta--;
                //showFPS++;
            }

            if (reachOne >= 1000000000)
            {
                //System.out.print(showFPS);
                reachOne = 0;
                //showFPS = 0;
            }
        }
    }

    public void startMyThread() 
    {
        myThread = new Thread(this);
        myThread.start();
    }
}
