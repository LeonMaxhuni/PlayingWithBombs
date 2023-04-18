import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Labels
{
    int x, y;
    int width, height;
    int type;
    int count;

    //Image countImage = new ImageIcon("textures//bombBlue.png").getImage();
    //Image count2Image = new ImageIcon("textures//bombPurple.png").getImage();

    Image unusedCount = new ImageIcon("textures//unusedBomb.png").getImage();
    Image unusedRange = new ImageIcon("textures//unusedRange.png").getImage();
    Image unusedSpeed = new ImageIcon("textures//unusedBoot.png").getImage();

    Image usedCount = new ImageIcon("textures//countPowerup.png").getImage();
    Image usedRange = new ImageIcon("textures//rangePowerup.png").getImage();
    Image usedSpeed = new ImageIcon("textures//bootPowerup.png").getImage();

    Image unusedTypes[] = {unusedCount, unusedRange, unusedSpeed};
    Image usedTypes[] = {usedCount, usedRange, usedSpeed};

    public Labels(int x, int y, int width, int height, int count, int type)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.count = count;
    }

    public void drawPowerups(Graphics2D g2, int condition, boolean alive)
    {
        if(count <= condition && alive == true)
        g2.drawImage(usedTypes[type], x, y, width, height, null);
        else
        drawPowerups(g2);
    }

    public void drawPowerups(Graphics2D g2)
    {
        g2.drawImage(unusedTypes[type], x, y, width, height, null);
    }
}