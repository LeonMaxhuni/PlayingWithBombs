import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ScorePanel extends JPanel
{
    final short frameWidth = 320;
    final short frameHeight = 832;

    Bomb bomb1[];
    Bomb bomb2[];
    Player player[];
    Labels allPowerLabel[][][];

    Image orangeCount = new ImageIcon("textures//orangeCountPowerup.png").getImage();
    Image orangeRange = new ImageIcon("textures//orangeRangePowerup.png").getImage();
    Image orangeSpeed = new ImageIcon("textures//orangeSpeedPowerup.png").getImage();

    Image purpleCount = new ImageIcon("textures//purpleCountPowerup.png").getImage();
    Image purpleRange = new ImageIcon("textures//purpleRangePowerup.png").getImage();
    Image purpleSpeed = new ImageIcon("textures//purpleSpeedPowerup.png").getImage();

    Image blueCount = new ImageIcon("textures//blueCountPowerup.png").getImage();
    Image blueRange = new ImageIcon("textures//blueRangePowerup.png").getImage();
    Image blueSpeed = new ImageIcon("textures//blueSpeedPowerup.png").getImage();

    Image pinkCount = new ImageIcon("textures//pinkCountPowerup.png").getImage();
    Image pinkRange = new ImageIcon("textures//pinkRangePowerup.png").getImage();
    Image pinkSpeed = new ImageIcon("textures//pinkSpeedPowerup.png").getImage();

    Image unusedCount = new ImageIcon("textures//unusedBomb.png").getImage();
    Image unusedRange = new ImageIcon("textures//unusedRange.png").getImage();
    Image unusedSpeed = new ImageIcon("textures//unusedBoot.png").getImage();

    Image orangePowerups[] = {orangeCount, orangeRange, orangeSpeed};
    Image purplePowerups[] = {purpleCount, purpleRange, purpleSpeed};
    Image bluePowerups[] = {blueCount, blueRange, blueSpeed};
    Image pinkPowerups[] = {pinkCount, pinkRange, pinkSpeed};
    Image unusedPowerups[] = {unusedCount, unusedRange, unusedSpeed};

    Image imageOfPowerup[][] = {orangePowerups, purplePowerups, bluePowerups, pinkPowerups, unusedPowerups};

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
                allPowerLabel[y][0][i].drawPowerups(g2, player[y].bombCount, player[y].alive, imageOfPowerup[y][0], imageOfPowerup[4][0]);
                allPowerLabel[y][1][i].drawPowerups(g2, player[y].bombRange, player[y].alive, imageOfPowerup[y][1], imageOfPowerup[4][1]);
                allPowerLabel[y][2][i].drawPowerups(g2, player[y].currentSpeed-1, player[y].alive, imageOfPowerup[y][2], imageOfPowerup[4][2]);
            }
            for(int y = player.length; y < allPowerLabel.length; y++)
            {
                allPowerLabel[y][0][i].drawPowerups(g2, imageOfPowerup[4][0]);
                allPowerLabel[y][1][i].drawPowerups(g2, imageOfPowerup[4][1]);
                allPowerLabel[y][2][i].drawPowerups(g2, imageOfPowerup[4][2]);
            }
        }
    }
}