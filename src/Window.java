import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame {

    GameClient gameClient;

    public Window(GameClient gameClient) {
        super(Constants.TITLE);
        this.gameClient = gameClient;
        setResizable(false);
        getContentPane().setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addWindowListener(new Listener());
    }

    class Listener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            gameClient.close();
        }
    }
}
