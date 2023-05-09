import java.awt.Graphics2D;
import java.awt.Image;

public class Labels
{
    int x, y;
    int width, height;
    int type;
    int count;

    //Image countImage = new ImageIcon("textures//bombBlue.png").getImage();
    //Image count2Image = new ImageIcon("textures//bombPurple.png").getImage();

    public Labels(int x, int y, int width, int height, int count, int type)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.count = count;
    }

    public void drawPowerups(Graphics2D g2, int condition, boolean alive, Image image, Image image2)
    {
        if(count <= condition && alive == true)
        g2.drawImage(image, x, y, width, height, null);
        else
        drawPowerups(g2, image2);
    }

    public void drawPowerups(Graphics2D g2, Image image)
    {
        g2.drawImage(image, x, y, width, height, null);
    }
}