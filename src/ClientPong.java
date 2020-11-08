import java.io.*;
import java.net.Socket;

public class ClientPong extends GameClient {

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientPong() {
        System.out.println("Attempting to connect to" +Constants.HOSTNAME+":"+Constants.PORT);
        try {
            socket = new Socket(Constants.HOSTNAME, Constants.PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        }catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connection Established");
    }

    @Override
    public void inputReceived(String s) {
        try{
            outputStream.reset();
            outputStream.writeObject(s);
            outputStream.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void packedReceived() {
        try {
            UpdatePacket update = (UpdatePacket) inputStream.readObject();
            setPlayer1(update.getPlayer1());
            setPlayer2(update.getPlayer2());
            setBall(update.getBall());
            setScore(new Score(update.getPlayer1(), update.getPlayer2()));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        board.repaint();
    }

    @Override
    public void close() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClientPong client = new ClientPong();
        while(true) {
            client.packedReceived();
        }
    }
}
