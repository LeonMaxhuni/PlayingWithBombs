import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keybindings implements KeyListener
{
    
    public boolean A, W, S, D, Q;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_A)
        {
            A = true;
        }
        if(code == KeyEvent.VK_W)
        {
            W = true;
        }
        if(code == KeyEvent.VK_S)
        {
            S = true;
        }
        if(code == KeyEvent.VK_D)
        {
            D = true;
        }
        if(code == KeyEvent.VK_Q)
        {
            Q = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_A)
        {
            A = false;
        }
        if(code == KeyEvent.VK_W)
        {
            W = false;
        }
        if(code == KeyEvent.VK_S)
        {
            S = false;
        }
        if(code == KeyEvent.VK_D)
        {
            D = false;
        }
        if(code == KeyEvent.VK_Q)
        {
            Q = false;
        }
    }
}