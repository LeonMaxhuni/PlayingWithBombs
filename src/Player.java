//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player
{
    Boolean[][] collisionInfo;
    int playerX, playerY;
    int blockPrevX, blockPrevY;
    int Xspeed, Yspeed;
    int color;
    int number;
    int currentSpeed = 2;
    int bombRange = 1;
    int bombCount = 1;
    int health = 3;
    long healthCooldown = 0;
    boolean alive = true;

    boolean[] binds;

    Image playerDown = new ImageIcon("textures//botPurpleDown.png").getImage();
    Image playerUp = new ImageIcon("textures//botPurpleUp.png").getImage();
    Image playerLeft = new ImageIcon("textures//botPurpleLeft.png").getImage();
    Image playerRight = new ImageIcon("textures//botPurpleRight.png").getImage();

    Image[] playersSides = {playerLeft, playerRight, playerUp, playerRight};

    Player(int blockX, int blockY, int color, int number)
    {
        this.playerX = (blockX-1)*64;
        this.playerY = (blockY-1)*64;
        this.blockPrevX = 10000;
        this.blockPrevY = 10000;
        this.color = color;
        this.number = number;
    }

    Player(int blockX, int blockY, int color, int number, boolean binds[])
    {
        this.playerX = (blockX-1)*64;
        this.playerY = (blockY-1)*64;
        this.blockPrevX = 10000;
        this.blockPrevY = 10000;
        this.color = color;
        this.number = number;
        this.binds = binds;
    }

    int getX()
    {
        return playerX;
    }

    int getY()
    {
        return playerY;
    }

    void increaseSpeed()
    {
        if(this.currentSpeed < 4)
        {
            this.currentSpeed++;
        }
    }

    void increaseBombRange()
    {
        if(this.bombRange < 3)
        {
            this.bombRange++;
        }
    }

    void increaseBombCount()
    {
        if(this.bombCount < 3)
        {
            this.bombCount++;
        }
    }

    void decreaseHealth()
    {
        if(health == 3)
        {
            health--;
            healthCooldown = System.currentTimeMillis() + 2000;
        }
        else if(System.currentTimeMillis() > healthCooldown && health == 2)
        {
            health --;
            healthCooldown = System.currentTimeMillis() + 2000;
        }
        else if(System.currentTimeMillis() > healthCooldown && health == 1)
        {
            health--;
        }
    }

    int getHealth()
    {
        return health;
    }

    int blockPlayerX()
    {
        return (playerX + 32)/64;
    }

    int blockPlayerY()
    {
        return (playerY + 32)/64;
    }

    boolean playerBlockXChanged(int blockPlayerX)
    {
        if(blockPrevX != blockPlayerX())
        {
            blockPrevX = blockPlayerX();
            return true;
        }
        return false;
    }

    boolean playerBlockYChanged(int blockPlayerY)
    {
        if(blockPrevY != blockPlayerY())
        {
            blockPrevY = blockPlayerY();
            return true;
        }
        return false;
    }

    void killPlayer()
    {
        alive = false;
        currentSpeed = 0;
        bombCount = 0;
    }

    void movePlayer(Map myMap)
    {
        if(binds[0] == true)
        {
            if(this.playerBlockXChanged(this.blockPlayerX()))
            {
                this.collisionInfo = myMap.getCollisionInfo(this);
                Objects.checkForObject(this);
            }
            CollisionDetection.checkDetectionLeft(this);
            this.playerX -= this.Xspeed;
        }
        if(binds[1] == true)
        {
            if(this.playerBlockXChanged(this.blockPlayerX()))
            {
                this.collisionInfo = myMap.getCollisionInfo(this);
                Objects.checkForObject(this);
            }
            CollisionDetection.checkDetectionRight(this);
            this.playerX += this.Xspeed;
        }
        if(binds[2] == true)
        {
            if(this.playerBlockYChanged(this.blockPlayerY()))
            {
                this.collisionInfo = myMap.getCollisionInfo(this);
                Objects.checkForObject(this);
            }
            CollisionDetection.checkDetectionTop(this);
            this.playerY -= this.Yspeed;
        }
        if(binds[3] == true)
        {
            if(this.playerBlockYChanged(this.blockPlayerY()))
            {
                this.collisionInfo = myMap.getCollisionInfo(this);
                Objects.checkForObject(this);
            }
            CollisionDetection.checkDetectionBottom(this);
            this.playerY += this.Yspeed;
        }
    }

    void placeBomb(Bomb[] bomb1, int[] activeBombsX, int[] activeBombsY)
    {
        if(binds[4] == true)
        {
            Bomb.prepareBomb(bomb1[0], bomb1[1], bomb1[2], this, activeBombsX, activeBombsY);
        }
    }

    int getDirection()
    {
        if(binds[0] == true)
        {
            return 0;
        }
        return 1;
    }

    void drawPlayer(Graphics2D g9)
    {
        if(alive == true)
        {
            g9.drawImage(playersSides[0], this.getX(), this.getY(), GamePanel.blockSize, GamePanel.blockSize, null);
            //g9.setColor(Color.RED);
            //g9.fillRect(this.getX(),this.getY(),GamePanel.blockSize,GamePanel.blockSize);
            if(health == 0)
            {
                killPlayer();
            }
        }
    }
}