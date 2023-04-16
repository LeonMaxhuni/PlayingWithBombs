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
    Labels player1Label[][][];

    public ScorePanel(Player players[], Bomb player1Bombs[], Labels player1Label[][][])
    {
        this.setBounds(960, 0, 320, 832);
        this.setBackground(Color.BLUE);
        this.setVisible(true);
        this.player = players;
        this.bomb1 = player1Bombs;
        this.player1Label = player1Label;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        for(int i = 0; i < 3; i++)
        {
            for(int y = 0; y < player1Label.length; y++)
            {
                player1Label[y][0][i].drawPowerups(g2, player[0].bombCount);
                player1Label[y][1][i].drawPowerups(g2, player[0].bombRange);
                player1Label[y][2][i].drawPowerups(g2, player[0].currentSpeed-1);
            }
        }
    }
}