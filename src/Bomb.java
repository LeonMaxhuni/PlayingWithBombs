import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Bomb
{
    int bombX, bombY;

    boolean state;
    int id;
    int color;
    
    static int litTime = 2000;
    long explosionTimer;

    Image bombOrange = new ImageIcon("textures//bombOrange.png").getImage();
    Image bombPurple = new ImageIcon("textures//bombPurple.png").getImage();
    Image bombBlue = new ImageIcon("textures//bombBlue.png").getImage();
    Image bombRed = new ImageIcon("textures//bombRed.png").getImage();

    Image[] bombs = {bombOrange,bombPurple,bombBlue,bombRed};

    Image dynamiteOrange = new ImageIcon("textures//dynamiteOrange.png").getImage();
    Image dynamitePurple = new ImageIcon("textures//dynamitePurple.png").getImage();
    Image dynamiteBlue = new ImageIcon("textures//dynamiteBlue.png").getImage();
    Image dynamiteRed = new ImageIcon("textures//dynamiteRed.png").getImage();

    Image[] dynamites = {dynamiteOrange,dynamitePurple,dynamiteBlue,dynamiteRed};

    Image dynamiteGlow1 = new ImageIcon("textures//dynamiteGlow1.png").getImage();
    Image dynamiteGlow2 = new ImageIcon("textures//dynamiteGlow2.png").getImage();
    Image dynamiteGlow3 = new ImageIcon("textures//dynamiteGlow3.png").getImage();

    Image[] dynamiteGlows = {dynamiteGlow1,dynamiteGlow2,dynamiteGlow3};

    Bomb(int color, int id)
    {
        this.id = id;
        this.color = color;
    }

    static void prepareBomb(Bomb bomb1, Bomb bomb2, Bomb bomb3, Bomb bomb4, Bomb bomb5, Bomb bomb6, Player player, int[] activeBombsX, int[] activeBombsY)
    {
        if(player.alive == true)
        {
            if(bomb1.state == false && Bomb.bombChecker(player, activeBombsX, activeBombsY) == true)
            {
                bomb1.deployBomb(player.blockPlayerX(), player.blockPlayerY());
                activeBombsX[0] = player.blockPlayerX();
                activeBombsY[0] = player.blockPlayerY();
                bomb1.explosionTimer = System.currentTimeMillis() + litTime;
            }
            else if(player.bombCount > 1 && bomb2.state == false && Bomb.bombChecker(player, activeBombsX, activeBombsY) == true)
            {
                bomb2.deployBomb(player.blockPlayerX(), player.blockPlayerY());
                activeBombsX[1] = player.blockPlayerX();
                activeBombsY[1] = player.blockPlayerY();
                bomb2.explosionTimer = System.currentTimeMillis() + litTime;
            }
            else if(player.bombCount > 2 && bomb3.state == false && Bomb.bombChecker(player, activeBombsX, activeBombsY) == true)
            {
                bomb3.deployBomb(player.blockPlayerX(), player.blockPlayerY());
                activeBombsX[2] = player.blockPlayerX();
                activeBombsY[2] = player.blockPlayerY();
                bomb3.explosionTimer = System.currentTimeMillis() + litTime;
            }
            else if(player.bombCount > 3 && bomb4.state == false && Bomb.bombChecker(player, activeBombsX, activeBombsY) == true)
            {
                bomb4.deployBomb(player.blockPlayerX(), player.blockPlayerY());
                activeBombsX[3] = player.blockPlayerX();
                activeBombsY[3] = player.blockPlayerY();
                bomb4.explosionTimer = System.currentTimeMillis() + litTime;
            }
            else if(player.bombCount > 4 && bomb5.state == false && Bomb.bombChecker(player, activeBombsX, activeBombsY) == true)
            {
                bomb5.deployBomb(player.blockPlayerX(), player.blockPlayerY());
                activeBombsX[4] = player.blockPlayerX();
                activeBombsY[4] = player.blockPlayerY();
                bomb5.explosionTimer = System.currentTimeMillis() + litTime;
            }
            else if(player.bombCount > 5 && bomb6.state == false && Bomb.bombChecker(player, activeBombsX, activeBombsY) == true)
            {
                bomb6.deployBomb(player.blockPlayerX(), player.blockPlayerY());
                activeBombsX[5] = player.blockPlayerX();
                activeBombsY[5] = player.blockPlayerY();
                bomb6.explosionTimer = System.currentTimeMillis() + litTime;
            }
        }
    }

    void deployBomb(int blockPlayerX, int blockPlayerY)
    {
        this.bombX = blockPlayerX;
        this.bombY = blockPlayerY;
        this.state = true;
    }

    void checkToRemove(Player player, int[] activeBombsX, int[] activeBombsY, Objects myObjects, Map myMap)
    {
        if(state == true && System.currentTimeMillis() > explosionTimer)
        {
            explodeBomb(activeBombsX, activeBombsY, player.bombRange);
            myObjects.checkPlayersForObjects();
            player.collisionInfo = myMap.getCollisionInfo(player);
        }
    }

    void explodeBomb(int activeBombsX[], int activeBombsY[], int bombRange)
    {
        this.state = false;
        Map.destroyWalls(this.bombX, this.bombY, bombRange, this.color);
        activeBombsX[this.id] = 10000 + id;
        activeBombsY[this.id] = 10000 + id;
    }

    static boolean bombChecker(Player player, int[] activeBombsX, int[] activeBombsY)
    {
        for(int i = 0; i < activeBombsX.length; i++)
        {
            if(player.blockPlayerX() == activeBombsX[i] && player.blockPlayerY() == activeBombsY[i])
            {
                return false;
            }
        }
        return true;
    }
    
    void drawBomb(Graphics2D g2)
    {
        if(state == true)
        {
            g2.drawImage(dynamites[1], Map.getTrueXY(this.bombX), Map.getTrueXY(this.bombY), GamePanel.blockSize, GamePanel.blockSize, null);
            g2.drawImage(dynamiteGlows[1], Map.getTrueXY(this.bombX), Map.getTrueXY(this.bombY), GamePanel.blockSize, GamePanel.blockSize, null);
        }
    }
}