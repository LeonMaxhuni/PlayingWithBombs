import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keybindings implements KeyListener
{
    
    boolean goLeft, goRight, goUp, goDown, placeBomb;
    boolean[] binds1 = {goLeft, goRight, goUp, goDown, placeBomb};

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_A)
        {
            binds1[0] = true;
        }
        if(code == KeyEvent.VK_D)
        {
            binds1[1] = true;
        }
        if(code == KeyEvent.VK_W)
        {
            binds1[2] = true;
        }
        if(code == KeyEvent.VK_S)
        {
            binds1[3] = true;
        }
        if(code == KeyEvent.VK_Q)
        {
            binds1[4] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_A)
        {
            binds1[0] = false;
        }
        if(code == KeyEvent.VK_D)
        {
            binds1[1] = false;
        }
        if(code == KeyEvent.VK_W)
        {
            binds1[2] = false;
        }
        if(code == KeyEvent.VK_S)
        {
            binds1[3] = false;
        }
        if(code == KeyEvent.VK_Q)
        {
            binds1[4] = false;
        }
    }
}