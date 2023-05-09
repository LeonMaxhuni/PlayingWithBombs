import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Objects {

    Player[] allPlayers;
    
    static int[][] objectDirection = new int[15][13];
    static int[][] objectLocation = new int[15][13];
    static int[][] objectColor = new int[15][13];
    static long[][] objectTimer = new long[15][13];
    static long smokeTime = 1200;

    static Image countImage = new ImageIcon("textures//countPowerup.png").getImage();
    static Image rangeImage = new ImageIcon("textures//rangePowerup.png").getImage();
    static Image bootImage = new ImageIcon("textures//bootPowerup.png").getImage();

    static Image purpleStill = new ImageIcon("textures//purpleSmokeStill.png").getImage();
    static Image purpleHorizontal = new ImageIcon("textures//purpleSmokeHorizontal.png").getImage();
    static Image purpleVertical = new ImageIcon("textures//purpleSmokeVertical.png").getImage();

    static Image orangeStill = new ImageIcon("textures//orangeSmokeStill.png").getImage();
    static Image orangeHorizontal = new ImageIcon("textures//orangeSmokeHorizontal.png").getImage();
    static Image orangeVertical = new ImageIcon("textures//orangeSmokeVertical.png").getImage();

    static Image[] orangeSmokes = {orangeStill, orangeHorizontal, orangeVertical};
    static Image[] purpleSmokes = {purpleStill, purpleHorizontal, purpleVertical};

    static Image[][] allSmokes = {orangeSmokes, purpleSmokes};

    Objects(Player[] allPlayers)
    {
        this.allPlayers = allPlayers;
    }

    public void checkPlayersForObjects()
    {
        for(int i = 0; i < allPlayers.length; i++)
        {
            checkForObject(allPlayers[i]);
        }
    }

    public static void addObject(int objX, int objY, int objectNumber)
    {
        if(objectLocation[objX][objY] != 5)
        {
            objectLocation[objX][objY] = objectNumber;
        }
    }

    public static void addObject(int objX, int objY, int objectNumber, int direction, int playerColor)
    {
        if(objectLocation[objX][objY] != 5)
        {
            objectLocation[objX][objY] = objectNumber;
            objectDirection[objX][objY] = direction;
            objectColor[objX][objY] = playerColor;
        }
    }

    public static void removeObject(int objX, int objY)
    {
        objectLocation[objX][objY] = 0;
    }

    public static int getObject(int x, int y)
    {
        return objectLocation[x][y];
    }

    public static void checkForObject(Player player)
    {
        switch(objectLocation[player.blockPlayerX()][player.blockPlayerY()])
        {
            case 0:
                break;
            case 1:
                removeObject(player.blockPlayerX(), player.blockPlayerY());
                player.increaseBombCount();
                break;
            case 2:
                removeObject(player.blockPlayerX(), player.blockPlayerY());
                player.increaseBombRange();
                break;
            case 3:
                removeObject(player.blockPlayerX(), player.blockPlayerY());
                player.increaseSpeed();
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
                    g2.drawImage(countImage, Map.getTrueXY(i), Map.getTrueXY(j), GamePanel.blockSize, GamePanel.blockSize, null);
                }
                if(objectLocation[i][j] == 2)
                {
                    g2.drawImage(rangeImage, Map.getTrueXY(i), Map.getTrueXY(j), GamePanel.blockSize, GamePanel.blockSize, null);
                }
                if(objectLocation[i][j] == 3)
                {
                    g2.drawImage(bootImage, Map.getTrueXY(i), Map.getTrueXY(j), GamePanel.blockSize, GamePanel.blockSize, null);
                }
                if(objectLocation[i][j] == 4 || objectLocation[i][j] == 5)
                {
                    objectLocation[i][j] += 2;
                    objectTimer[i][j] = System.currentTimeMillis() + smokeTime;
                }
                if(objectLocation[i][j] == 6 || objectLocation[i][j] == 7)
                {
                    if(System.currentTimeMillis() < objectTimer[i][j])
                    {
                        g2.drawImage(allSmokes[objectColor[i][j]][objectDirection[i][j]], Map.getTrueXY(i), Map.getTrueXY(j), GamePanel.blockSize, GamePanel.blockSize, null);
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
