import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Labels
{
    int x, y;
    int width, height;
    int type;
    int count;

    Image countImage = new ImageIcon("textures//bombBlue.png").getImage();
    Image count2Image = new ImageIcon("textures//bombPurple.png").getImage();

    public Labels(int x, int y, int width, int height, int count, int type)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.count = count;
    }

    public void drawPowerups(Graphics2D g2, int condition)
    {
        if(count <= condition)
        g2.drawImage(countImage, x, y, width, height, null);
        else
        g2.drawImage(count2Image, x, y, width, height, null);
    }
}