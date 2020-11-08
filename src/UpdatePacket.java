import java.io.Serializable;

public class UpdatePacket implements Serializable {
    private Player player1;
    private Player player2;
    private Ball ball;

    public UpdatePacket(Ball ball, Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.ball = ball;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Ball getBall() {
        return ball;
    }
}
