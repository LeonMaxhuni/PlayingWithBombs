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

    Objects myObjects;
    Map myMap;
    Keybindings myBinds;

    int[] activeBombsX = new int[8];
    int[] activeBombsY = new int[8];

    Bomb bomb1[];
    Bomb bomb2[];
    Player player[];

    public GamePanel(Player player[], Bomb bomb1[], Objects myObjects ,Map myMap, Keybindings myBinds)
    {
        this.setBounds(0, 0, panelWidth, panelHeight);
        this.setBackground(Color.GREEN);
        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(myBinds);
        this.player = player;
        this.bomb1 = bomb1;
        this.myObjects = myObjects;
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
        bomb1[0].checkToRemove(player[0], activeBombsX, activeBombsY, myObjects ,myMap);
        bomb1[1].checkToRemove(player[0], activeBombsX, activeBombsY, myObjects ,myMap);
        bomb1[2].checkToRemove(player[0], activeBombsX, activeBombsY, myObjects ,myMap);
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