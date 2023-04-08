import javax.swing.*;

public class Main extends JFrame
{

    public static void main(String[] args)
    {

        JFrame myFrame = new JFrame();

        myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        myFrame.setResizable(false);
        myFrame.setLocationRelativeTo(null);
        myFrame.setTitle("PlayingWithBombs");

        GamePanel myPanel = new GamePanel();

        myFrame.add(myPanel);
        
        myFrame.pack();
        myFrame.setVisible(true);
        myPanel.startMyThread();
    }
}