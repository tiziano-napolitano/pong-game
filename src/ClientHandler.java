import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private Player player;
    private Socket socket;

    public ClientHandler(Socket socket, Player player) {
        this.player = player;
        this.socket = socket;
        try {
            outputStream = new ObjectOutputStream(this.socket.getOutputStream());
            inputStream = new ObjectInputStream(this.socket.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void sendPacket(Object obj) {
        try{
            outputStream.reset();
            outputStream.writeObject(obj);
            outputStream.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        boolean running = true;

        while(running) {
            try {
                String userInput = (String) inputStream.readObject();
                if(userInput!=null) {
                    System.out.println("SERVER RICEVE: "+userInput);
                    if(userInput.equals("w")) {
                        player.setKeyPressed(KeyEvent.VK_W);
                        player.setKeyReleased();
                    } else if(userInput.equals("s")) {
                        player.setKeyPressed(KeyEvent.VK_S);
                        player.setKeyReleased();
                    }
                }
            } catch (EOFException | SocketException e){
                running = false;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
