import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class ScorePanel extends JPanel
{
    final short frameWidth = 320;
    final short frameHeight = 832;

    Bomb bomb1[];
    Bomb bomb2[];
    Player player[];
    Labels allPowerLabel[][][];

    public ScorePanel(Player players[], Bomb player1Bombs[], Labels allPowerLabel[][][])
    {
        this.setBounds(960, 0, 320, 832);
        this.setBackground(Color.BLUE);
        this.setVisible(true);
        this.player = players;
        this.bomb1 = player1Bombs;
        this.allPowerLabel = allPowerLabel;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        
        for(int i = 0; i < 3; i++)
        {
            for(int y = 0; y < player.length; y++)
            {
                allPowerLabel[y][0][i].drawPowerups(g2, player[y].bombCount);
                allPowerLabel[y][1][i].drawPowerups(g2, player[y].bombRange);
                allPowerLabel[y][2][i].drawPowerups(g2, player[y].currentSpeed-1);
            }
            for(int y = player.length; y < allPowerLabel.length; y++)
            {
                allPowerLabel[y][0][i].drawPowerups(g2);
                allPowerLabel[y][1][i].drawPowerups(g2);
                allPowerLabel[y][2][i].drawPowerups(g2);
            }
        }
    }
}