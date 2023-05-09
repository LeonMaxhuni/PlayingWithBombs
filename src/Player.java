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
    int currentSpeed = 3;
    int bombRange = 1;
    int bombCount = 1;
    int health = 3;
    long healthCooldown = 1000;
    boolean alive = true;
    int direction;

    boolean[] binds;

    Image playerDown = new ImageIcon("textures//botPurpleDown.png").getImage();
    Image playerUp = new ImageIcon("textures//botPurpleUp.png").getImage();
    Image playerLeft = new ImageIcon("textures//botPurpleLeft.png").getImage();
    Image playerRight = new ImageIcon("textures//botPurpleRight.png").getImage();

    Image[] playersSides = {playerLeft, playerRight, playerUp, playerDown};

    Player(int blockX, int blockY, int color, int number)
    {
        this.playerX = (blockX-1)*64;
        this.playerY = (blockY-1)*64;
        this.blockPrevX = 10000;
        this.blockPrevY = 10000;
        this.color = color;
        this.number = number;
        this.direction = 3;
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
        this.direction = 3;
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
        if(this.currentSpeed < 3)
        {
            this.currentSpeed++;
        }
    }

    void increaseBombRange()
    {
        if(this.bombRange < 4)
        {
            this.bombRange++;
        }
    }

    void increaseBombCount()
    {
        if(this.bombCount < 4)
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

    boolean playerBlockXChanged()
    {
        if(blockPrevX != blockPlayerX())
        {
            blockPrevX = blockPlayerX();
            return true;
        }
        return false;
    }

    boolean playerBlockYChanged()
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
        direction = 3;
        if(binds[2] == true)
        {
            if(playerBlockYChanged())
            {
                collisionInfo = myMap.getCollisionInfo(this);
                Objects.checkForObject(this);
            }
            CollisionDetection.checkDetectionTop(this);
            playerY -= Yspeed;
            direction = 2;
        }
        if(binds[3] == true)
        {
            if(playerBlockYChanged())
            {
                collisionInfo = myMap.getCollisionInfo(this);
                Objects.checkForObject(this);
            }
            CollisionDetection.checkDetectionBottom(this);
            playerY += Yspeed;
            direction = 3;
        }
        if(binds[0] == true)
        {
            if(playerBlockXChanged())
            {
                collisionInfo = myMap.getCollisionInfo(this);
                Objects.checkForObject(this);
            }
            CollisionDetection.checkDetectionLeft(this);
            playerX -= Xspeed;
            direction = 0;
        }
        if(binds[1] == true)
        {
            if(playerBlockXChanged())
            {
                collisionInfo = myMap.getCollisionInfo(this);
                Objects.checkForObject(this);
            }
            CollisionDetection.checkDetectionRight(this);
            playerX += Xspeed;
            direction = 1;
        }
        if(binds[0] == true && binds[1] == true)
        {
            direction = 3;
        }
    }

    void placeBomb(Bomb[] bomb1, int[] activeBombsX, int[] activeBombsY)
    {
        if(binds[4] == true)
        {
            Bomb.prepareBomb(bomb1[0], bomb1[1], bomb1[2], bomb1[3], bomb1[4], bomb1[5], this, activeBombsX, activeBombsY);
        }
    }

    void drawPlayer(Graphics2D g9)
    {
        if(alive == true)
        {
            g9.drawImage(playersSides[direction], this.getX(), this.getY(), GamePanel.blockSize, GamePanel.blockSize, null);
            //g9.setColor(Color.RED);
            //g9.fillRect(this.getX(),this.getY(),GamePanel.blockSize,GamePanel.blockSize);
            if(health == 0)
            {
                killPlayer();
            }
        }
    }
}