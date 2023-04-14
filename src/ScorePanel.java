import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ScorePanel extends JPanel
{
    final short frameWidth = 320;
    final short frameHeight = 832;

    Bomb bomb1[];
    Bomb bomb2[];
    Player player[];

    ImageIcon p1 = new ImageIcon("textures//bombBlue.png");

    public ScorePanel(Player players[], Bomb player1Bombs[])
    {
        this.setBounds(960, 0, 320, 832);
        this.setBackground(Color.BLUE);
        this.setVisible(true);
        this.setOpaque(true);
        this.player = players;
        this.bomb1 = player1Bombs;
    }
}