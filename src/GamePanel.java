import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.security.Key;

public class GamePanel extends JPanel
{
    final short panelWidth = 960;
    final short panelHeight = 832;

    final static byte blockSize = 64;

    Map myMap;
    Keybindings myBinds;

    int[] activeBombsX = new int[8];
    int[] activeBombsY = new int[8];

    Bomb bomb1[];
    Bomb bomb2[];
    Player player[];

    public GamePanel(Player player[], Bomb bomb1[], Map myMap, Keybindings myBinds)
    {
        this.setBounds(0, 0, panelWidth, panelHeight);
        this.setBackground(Color.GREEN);
        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(myBinds);
        this.player = player;
        this.bomb1 = bomb1;
        this.myMap = myMap;
        this.myBinds = myBinds;
    }

    public void update()
    {
        //System.out.println(myBinds.binds1[2]);
        for(int i = 0; i < 1; i++)
        {
            player[i].movePlayer(myMap);
            player[i].placeBomb(bomb1, activeBombsX, activeBombsY);
        }

        if(bomb1[0].state == true && System.currentTimeMillis() > bomb1[0].explosionTimer)
        {
            bomb1[0].explodeBomb(activeBombsX, activeBombsY, player[0].bombRange);
            checkPlayersForObjects(player);
            player[0].collisionInfo = myMap.getCollisionInfo(player[0]);
        }
        if(bomb1[1].state == true && System.currentTimeMillis() > bomb1[1].explosionTimer)
        {
            bomb1[1].explodeBomb(activeBombsX, activeBombsY, player[0].bombRange);
            checkPlayersForObjects(player);
            player[0].collisionInfo = myMap.getCollisionInfo(player[0]);
        }
        if(bomb1[2].state == true && System.currentTimeMillis() > bomb1[2].explosionTimer)
        {
            bomb1[2].explodeBomb(activeBombsX, activeBombsY, player[0].bombRange);
            checkPlayersForObjects(player);
            player[0].collisionInfo = myMap.getCollisionInfo(player[0]);
        }
    }

    public void checkPlayersForObjects(Player player[])
    {
        Objects.checkForObject(player[0]);
        Objects.checkForObject(player[1]);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

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