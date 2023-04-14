import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel
{
    final short frameWidth = 960;
    final short frameHeight = 832;

    final static byte blockSize = 64;

    Map myMap;
    Keybindings myBinds = new Keybindings();
    boolean mapLoaded = false;

    Bomb bomb1[];
    Bomb bomb2[];
    Player player[];

    int[] activeBombsX = new int[8];
    int[] activeBombsY = new int[8];

    public GamePanel(Player players[], Bomb player1Bombs[])
    {
        this.setBounds(0, 0, 960, 832);
        this.setBackground(Color.GREEN);
        this.setVisible(true);
        this.setOpaque(true);
        this.setFocusable(true);
        this.addKeyListener(myBinds);
        this.player = players;
        this.bomb1 = player1Bombs;
    }

    public void update()
    {
        if(myBinds.A == true)
        {
            if(player[0].playerBlockXChanged(player[0].blockPlayerX()))
            {
                player[0].collisionInfo = myMap.getCollisionInfo(player[0]);
                Objects.checkForObject(player[0]);
            }
            CollisionDetection.checkDetectionLeft(player[0]);
            player[0].playerX -= player[0].Xspeed;
        }
        if(myBinds.W == true)
        {
            if(player[0].playerBlockYChanged(player[0].blockPlayerY()))
            {
                player[0].collisionInfo = myMap.getCollisionInfo(player[0]);
                Objects.checkForObject(player[0]);
            }
            CollisionDetection.checkDetectionTop(player[0]);
            player[0].playerY -= player[0].Yspeed;
        }
        if(myBinds.S == true)
        {
            if(player[0].playerBlockYChanged(player[0].blockPlayerY()))
            {
                player[0].collisionInfo = myMap.getCollisionInfo(player[0]);
                Objects.checkForObject(player[0]);
            }
            CollisionDetection.checkDetectionBottom(player[0]);
            player[0].playerY += player[0].Yspeed;
        }
        if(myBinds.D == true)
        {
            if(player[0].playerBlockXChanged(player[0].blockPlayerX()))
            {
                player[0].collisionInfo = myMap.getCollisionInfo(player[0]);
                Objects.checkForObject(player[0]);
            }
            CollisionDetection.checkDetectionRight(player[0]);
            player[0].playerX += player[0].Xspeed;
        }
        if(myBinds.Q == true)
        {
            Bomb.prepareBomb(bomb1[0], bomb1[1], bomb1[2], player[0], activeBombsX, activeBombsY);
        }

        if(bomb1[0].state == true && System.currentTimeMillis() > bomb1[0].explosionTimer)
        {
            bomb1[0].explodeBomb(activeBombsX, activeBombsY, player[0].bombRange);
            checkPlayersForObjects();
            player[0].collisionInfo = myMap.getCollisionInfo(player[0]);
        }
        if(bomb1[1].state == true && System.currentTimeMillis() > bomb1[1].explosionTimer)
        {
            bomb1[1].explodeBomb(activeBombsX, activeBombsY, player[0].bombRange);
            checkPlayersForObjects();
            player[0].collisionInfo = myMap.getCollisionInfo(player[0]);
        }
        if(bomb1[2].state == true && System.currentTimeMillis() > bomb1[2].explosionTimer)
        {
            bomb1[2].explodeBomb(activeBombsX, activeBombsY, player[0].bombRange);
            checkPlayersForObjects();
            player[0].collisionInfo = myMap.getCollisionInfo(player[0]);
        }
    }

    public void checkPlayersForObjects()
    {
        Objects.checkForObject(player[0]);
        Objects.checkForObject(player[1]);
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
            System.out.println("MAPPY");
        }

        Graphics2D g2 = (Graphics2D)g;
        myMap.startClosingMap();
        myMap.drawMap(g2);
        Objects.drawObject(g2);

        Graphics2D g3 = (Graphics2D)g;
        bomb1[0].drawBomb(g3);
        bomb1[1].drawBomb(g3);
        bomb1[2].drawBomb(g3);

        Graphics2D g9 = (Graphics2D)g;
        player[0].drawPlayer(g9);
        player[1].drawPlayer(g9);
    }
}