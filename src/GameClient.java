import java.awt.*;

public abstract class GameClient {

    private Window window;
    protected Board board;

    private Player player1;
    private Player player2;
    private Ball ball;
    private Score score;

    public GameClient() {
        window = new Window(this);
        board = new Board(this);
        board.setBackground(Color.black);
        window.add(board);
        window.setVisible(true);
        player1 = new Player(20,(Constants.HEIGHT/2)-(Constants.PADDLE_HEIGHT/2),Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT,1);
        player2 = new Player((Constants.WIDTH - Constants.PADDLE_WIDTH)-20,(Constants.HEIGHT/2)-(Constants.PADDLE_HEIGHT/2),Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT,2);
        ball = new Ball((Constants.WIDTH/2)-(Constants.BALL_DIAMETER/2),25,Constants.BALL_DIAMETER,Constants.BALL_DIAMETER);
        score = new Score(player1,player2);
    }

    public abstract void inputReceived(String s);
    public abstract void packedReceived();
    public abstract void close();


    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Ball getBall() {
        return ball;
    }

    public Score getScore() {
        return score;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
