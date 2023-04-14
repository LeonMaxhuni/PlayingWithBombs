import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Objects {
    
    static int[][] objectLocation = new int[15][13];
    static long[][] objectTimer = new long[15][13];

    static Image image4 = new ImageIcon("textures//differentstatic3.png").getImage();
    static Image image5 = new ImageIcon("textures//temp.png").getImage();

    Objects()
    {

    }

    public static void addObject(int objX, int objY, int objectNumber)
    {
        if(objectLocation[objX][objY] != 5)
        {
            objectLocation[objX][objY] = objectNumber;
        }
    }

    public static void removeObject(int objX, int objY)
    {
        objectLocation[objX][objY] = 0;
    }

    public static void checkForObject(Player player)
    {
        switch(objectLocation[player.blockPlayerX()][player.blockPlayerY()])
        {
            case 0:
                break;
            case 1:
                removeObject(player.blockPlayerX(), player.blockPlayerY());
                player.increaseSpeed();
                break;
            case 2:
                removeObject(player.blockPlayerX(), player.blockPlayerY());
                player.increaseBombRange();
                break;
            case 3:
                removeObject(player.blockPlayerX(), player.blockPlayerY());
                player.increaseBombCount();
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                player.decreaseHealth();
                //player.printHealth();
                break;
        }
    }

    public static void drawObject(Graphics2D g2)
    {
        for(int i = 0; i < objectLocation.length; i++)
        {
            for(int j = 0; j < objectLocation[j].length; j++)
            {
                if(objectLocation[i][j] == 1)
                {
                    g2.drawImage(image4, Map.getTrueXY(i), Map.getTrueXY(j), GamePanel.blockSize, GamePanel.blockSize, null);
                }
                if(objectLocation[i][j] == 2)
                {
                    g2.drawImage(image4, Map.getTrueXY(i), Map.getTrueXY(j), GamePanel.blockSize, GamePanel.blockSize, null);
                }
                if(objectLocation[i][j] == 3)
                {
                    g2.drawImage(image4, Map.getTrueXY(i), Map.getTrueXY(j), GamePanel.blockSize, GamePanel.blockSize, null);
                }
                if(objectLocation[i][j] == 4 || objectLocation[i][j] == 5)
                {
                    objectLocation[i][j] += 2;
                    objectTimer[i][j] = System.currentTimeMillis()+2000;
                }
                if(objectLocation[i][j] == 6 || objectLocation[i][j] == 7)
                {
                    if(System.currentTimeMillis() < objectTimer[i][j])
                    {
                        g2.drawImage(image5, Map.getTrueXY(i), Map.getTrueXY(j), GamePanel.blockSize, GamePanel.blockSize, null);
                    }
                    else
                    {
                        if(objectLocation[i][j] == 7)
                        {
                            objectLocation[i][j] = 0;
                            Powerups.generateRandomPowerup(i, j);
                        }
                        else
                        {
                            objectLocation[i][j] = 0;
                        }
                    }
                }
            }
        }
    }
}
