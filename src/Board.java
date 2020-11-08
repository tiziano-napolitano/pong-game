import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private GameClient gameClient;

    public Board(GameClient gameClient) {
        this.gameClient = gameClient;
        addKeyListener(new KeyLogic(gameClient));
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }

    public void draw(Graphics g) {
        gameClient.getPlayer1().draw(g);
        gameClient.getPlayer2().draw(g);
        gameClient.getBall().draw(g);
        gameClient.getScore().draw(g);
    }
}
