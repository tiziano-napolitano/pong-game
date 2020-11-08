import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

public class GameServer implements Runnable {

    private Ball ball;
    private Player player1, player2;
    private ClientHandler clientHandler1;
    private ClientHandler clientHandler2;

    Thread gameThread;

    public GameServer(ClientHandler clientHandler1, ClientHandler clientHandler2, Player player1, Player player2, Ball ball) {
        this.player1 = player1;
        this.player2 = player2;
        this.ball = ball;
        this.clientHandler1 = clientHandler1;
        this.clientHandler2 = clientHandler2;

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall(){
        ball = new Ball((Constants.WIDTH/2)-(Constants.BALL_DIAMETER/2),new Random().nextInt(Constants.HEIGHT-Constants.BALL_DIAMETER),Constants.BALL_DIAMETER,Constants.BALL_DIAMETER);
    }

    public void checkCollision() {
        if(ball.y<=0){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= Constants.HEIGHT-Constants.BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }

        if(ball.intersects(player1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if(ball.intersects(player2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if(player1.y<=0)
            player1.y=0;
        if(player1.y >= (Constants.HEIGHT-Constants.PADDLE_HEIGHT))
            player1.y = Constants.HEIGHT-Constants.PADDLE_HEIGHT;
        if(player2.y<=0)
            player2.y=0;
        if(player2.y >= (Constants.HEIGHT-Constants.PADDLE_HEIGHT))
            player2.y = Constants.HEIGHT-Constants.PADDLE_HEIGHT;

        if(ball.x <=0) {
            player2.incrementScore();
            newBall();
        }
        if(ball.x >= Constants.WIDTH-Constants.BALL_DIAMETER) {
            player1.incrementScore();
            newBall();
        }

    }

    public void move(){
        player1.move();
        player2.move();
        ball.move();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                move();
                checkCollision();
                delta--;
                UpdatePacket update = new UpdatePacket(ball, player1, player2);
                clientHandler1.sendPacket(update);
                clientHandler2.sendPacket(update);
            }
        }
    }
}
