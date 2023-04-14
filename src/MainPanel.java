import java.awt.Color;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class MainPanel extends JPanel implements Runnable
{    
    final short frameWidth = 1280;
    final short frameHeight = 832;
    private GamePanel gamePanel;

    short FPS = 60;
    Thread myThread;

    public MainPanel(JPanel panel1, JPanel panel2)
    {
        this.setPreferredSize(new DimensionUIResource(frameWidth,frameHeight));
        this.setBackground(Color.YELLOW);
        this.setVisible(true);
        this.setOpaque(true);
        this.setLayout(null);
        gamePanel = (GamePanel)panel1;
        this.add(panel1);
        this.add(panel2);
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
                gamePanel.update();
                gamePanel.repaint();
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
