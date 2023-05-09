import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keybindings implements KeyListener
{
    
    boolean goLeft, goRight, goUp, goDown, placeBomb;
    boolean[] binds1 = {goLeft, goRight, goUp, goDown, placeBomb};

    boolean goLeft2, goRight2, goUp2, goDown2, placeBomb2;
    boolean[] binds2 = {goLeft, goRight, goUp, goDown, placeBomb};

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

        if(code == KeyEvent.VK_LEFT)
        {
            binds2[0] = true;
        }
        if(code == KeyEvent.VK_RIGHT)
        {
            binds2[1] = true;
        }
        if(code == KeyEvent.VK_UP)
        {
            binds2[2] = true;
        }
        if(code == KeyEvent.VK_DOWN)
        {
            binds2[3] = true;
        }
        if(code == KeyEvent.VK_SPACE)
        {
            binds2[4] = true;
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

        if(code == KeyEvent.VK_LEFT)
        {
            binds2[0] = false;
        }
        if(code == KeyEvent.VK_RIGHT)
        {
            binds2[1] = false;
        }
        if(code == KeyEvent.VK_UP)
        {
            binds2[2] = false;
        }
        if(code == KeyEvent.VK_DOWN)
        {
            binds2[3] = false;
        }
        if(code == KeyEvent.VK_SPACE)
        {
            binds2[4] = false;
        }
    }
}