import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class KeyLogic implements ActionListener, KeyListener {
    private GameClient gameClient;

    public KeyLogic(GameClient gameClient) {
        this.gameClient = gameClient;
        Timer timer = new Timer(1000/70, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
                gameClient.inputReceived("w");
                break;
            case KeyEvent.VK_S:
                gameClient.inputReceived("s");
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
