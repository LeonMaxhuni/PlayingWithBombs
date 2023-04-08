import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable
{
    final short frameWidth = 960;
    final short frameHeight = 832;

    final static byte blockSize = 64;

    short FPS = 60;

    Thread myThread;
    Map myMap;
    Keybindings myBinds = new Keybindings();
    boolean mapLoaded = false;

    Player player1 = new Player(2, 2, 0, 1);
    Player player2 = new Player(10, 10, 1, 2);

    Player allPlayers[] = {player1,player2};

    Bomb bomb1 = new Bomb(player1.color, 0);
    Bomb bomb2 = new Bomb(player1.color, 1);
    Bomb bomb3 = new Bomb(player1.color, 2);
    int[] activeBombsX = new int[8];
    int[] activeBombsY = new int[8];

    public GamePanel()
    {
        this.setPreferredSize(new DimensionUIResource(frameWidth,frameHeight));
        this.setBackground(Color.GREEN);
        this.setVisible(true);
        this.setOpaque(true);
        this.setFocusable(true);
        this.addKeyListener(myBinds);
    }

    public void startMyThread() 
    {
        myThread = new Thread(this);
        myThread.start();
    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long reachOne = 0;
        //int showFPS = 0;

        while(myThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            reachOne += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1)
            {
                update();
                repaint();
                delta--;
                //showFPS++;
            }

            if (reachOne >= 1000000000)
            {
                //System.out.print(showFPS);
                reachOne = 0;
                //showFPS = 0;
            }
        }
    }

    public void checkPlayersForObjects()
    {
        Objects.checkForObject(player1);
        Objects.checkForObject(player2);
    }

    public void update()
    {
        if(myBinds.A == true)
        {
            if(player1.playerBlockXChanged(player1.blockPlayerX()))
            {
                player1.collisionInfo = myMap.getCollisionInfo(player1);
                Objects.checkForObject(player1);
            }
            CollisionDetection.checkDetectionLeft(player1);
            player1.playerX -= player1.Xspeed;
        }
        if(myBinds.W == true)
        {
            if(player1.playerBlockYChanged(player1.blockPlayerY()))
            {
                player1.collisionInfo = myMap.getCollisionInfo(player1);
                Objects.checkForObject(player1);
            }
            CollisionDetection.checkDetectionTop(player1);
            player1.playerY -= player1.Yspeed;
        }
        if(myBinds.S == true)
        {
            if(player1.playerBlockYChanged(player1.blockPlayerY()))
            {
                player1.collisionInfo = myMap.getCollisionInfo(player1);
                Objects.checkForObject(player1);
            }
            CollisionDetection.checkDetectionBottom(player1);
            player1.playerY += player1.Yspeed;
        }
        if(myBinds.D == true)
        {
            if(player1.playerBlockXChanged(player1.blockPlayerX()))
            {
                player1.collisionInfo = myMap.getCollisionInfo(player1);
                Objects.checkForObject(player1);
            }
            CollisionDetection.checkDetectionRight(player1);
            player1.playerX += player1.Xspeed;
        }
        if(myBinds.Q == true)
        {
            Bomb.prepareBomb(bomb1, bomb2, bomb3, player1, activeBombsX, activeBombsY);
        }

        if(bomb1.state == true && System.currentTimeMillis() > bomb1.explosionTimer)
        {
            bomb1.explodeBomb(activeBombsX, activeBombsY, player1.bombRange);
            checkPlayersForObjects();
            player1.collisionInfo = myMap.getCollisionInfo(player1);
        }
        if(bomb2.state == true && System.currentTimeMillis() > bomb2.explosionTimer)
        {
            bomb2.explodeBomb(activeBombsX, activeBombsY, player1.bombRange);
            checkPlayersForObjects();
            player1.collisionInfo = myMap.getCollisionInfo(player1);
        }
        if(bomb3.state == true && System.currentTimeMillis() > bomb3.explosionTimer)
        {
            bomb3.explodeBomb(activeBombsX, activeBombsY, player1.bombRange);
            checkPlayersForObjects();
            player1.collisionInfo = myMap.getCollisionInfo(player1);
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (mapLoaded == false)
        try
        {
            myMap = new Map();
            mapLoaded = true;
        }
        catch (Exception e)
        {
            System.out.println("SOMETHING IS WORNG WITH MY MAP");
        }

        Graphics2D g2 = (Graphics2D)g;
        myMap.drawMap(g2);
        Objects.drawObject(g2);

        Graphics2D g3 = (Graphics2D)g;
        bomb1.drawBomb(g3);
        bomb2.drawBomb(g3);
        bomb3.drawBomb(g3);

        Graphics2D g9 = (Graphics2D)g;
        player1.drawPlayer(g9);
        player2.drawPlayer(g9);
    }
}