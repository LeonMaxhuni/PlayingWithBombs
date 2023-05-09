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

    int[] activeBombsX = new int[12];
    int[] activeBombsY = new int[12];

    Bomb allBombs[][];
    Player allPlayers[];

    public GamePanel(Player allPlayers[], Bomb allBombs[][], Objects myObjects , Map myMap, Keybindings myBinds)
    {
        this.setBounds(0, 0, panelWidth, panelHeight);
        this.setBackground(Color.GREEN);
        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(myBinds);
        this.allPlayers = allPlayers;
        this.allBombs = allBombs;
        this.myObjects = myObjects;
        this.myMap = myMap;
        this.myBinds = myBinds;
    }

    public void update()
    {
        //System.out.println(myBinds.binds1[2]);
        for(int i = 0; i < allPlayers.length; i++)
        {
            allPlayers[i].movePlayer(myMap);
            allPlayers[i].placeBomb(allBombs[i], activeBombsX, activeBombsY);
        }
        allBombs[0][0].checkToRemove(allPlayers[0], activeBombsX, activeBombsY, myObjects, myMap);
        allBombs[0][1].checkToRemove(allPlayers[0], activeBombsX, activeBombsY, myObjects, myMap);
        allBombs[0][2].checkToRemove(allPlayers[0], activeBombsX, activeBombsY, myObjects, myMap);
        allBombs[0][3].checkToRemove(allPlayers[0], activeBombsX, activeBombsY, myObjects, myMap);
        allBombs[0][4].checkToRemove(allPlayers[0], activeBombsX, activeBombsY, myObjects, myMap);
        allBombs[0][5].checkToRemove(allPlayers[0], activeBombsX, activeBombsY, myObjects, myMap);

        allBombs[1][0].checkToRemove(allPlayers[1], activeBombsX, activeBombsY, myObjects, myMap);
        allBombs[1][1].checkToRemove(allPlayers[1], activeBombsX, activeBombsY, myObjects, myMap);
        allBombs[1][2].checkToRemove(allPlayers[1], activeBombsX, activeBombsY, myObjects, myMap);
        allBombs[1][3].checkToRemove(allPlayers[1], activeBombsX, activeBombsY, myObjects, myMap);
        allBombs[1][4].checkToRemove(allPlayers[1], activeBombsX, activeBombsY, myObjects, myMap);
        allBombs[1][5].checkToRemove(allPlayers[1], activeBombsX, activeBombsY, myObjects, myMap);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        myMap.startClosingMap();
        myMap.drawMap(g2);
        Objects.drawObject(g2);

        Graphics2D g3 = (Graphics2D)g;
        allBombs[0][0].drawBomb(g3);
        allBombs[0][1].drawBomb(g3);
        allBombs[0][2].drawBomb(g3);
        allBombs[0][3].drawBomb(g3);
        allBombs[0][4].drawBomb(g3);
        allBombs[0][5].drawBomb(g3);

        allBombs[1][0].drawBomb(g3);
        allBombs[1][1].drawBomb(g3);
        allBombs[1][2].drawBomb(g3);
        allBombs[1][3].drawBomb(g3);
        allBombs[1][4].drawBomb(g3);
        allBombs[1][5].drawBomb(g3);

        Graphics2D g9 = (Graphics2D)g;
        allPlayers[0].drawPlayer(g9);
        allPlayers[1].drawPlayer(g9);
    }
}