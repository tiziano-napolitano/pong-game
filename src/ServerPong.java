import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPong {


    private Player player1;
    private Player player2;
    private Ball ball;

    private ClientHandler clientHandler1;
    private ClientHandler clientHandler2;

    private ServerSocket serverSocket;
    private GameServer gameServer;
    private Socket socket;

    public ServerPong() {
        player1 = new Player(20,(Constants.HEIGHT/2)-(Constants.PADDLE_HEIGHT/2),Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT,1);
        player2 = new Player((Constants.WIDTH - Constants.PADDLE_WIDTH)-20,(Constants.HEIGHT/2)-(Constants.PADDLE_HEIGHT/2),Constants.PADDLE_WIDTH,Constants.PADDLE_HEIGHT,2);
        ball = new Ball((Constants.WIDTH/2)-(Constants.BALL_DIAMETER/2),25,Constants.BALL_DIAMETER,Constants.BALL_DIAMETER);
        gameServer = null;
        try {
            serverSocket = new ServerSocket(Constants.PORT);
            int n_player = 0;
            Thread t1 = null ,t2 = null;
            while(n_player<2) {
                System.out.println("Waiting for clients...");
                socket = serverSocket.accept();
                System.out.println("Thr following client has connected "+socket.getInetAddress().getCanonicalHostName());

                if(n_player==0) {
                    t1 = new Thread(clientHandler1 = new ClientHandler(socket,player1));
                    t1.start();
                    n_player++;
                } else if(n_player==1) {
                    t2 = new Thread(clientHandler2 = new ClientHandler(socket,player2));
                    t2.start();
                    gameServer = new GameServer(clientHandler1,clientHandler2,player1,player2,ball);
                    n_player++;
                }
            }

        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServerPong();
    }
}
