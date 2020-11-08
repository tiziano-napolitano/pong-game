import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Rectangle {
    int id;
    int score;
    int yVelocity;
    int speed = 10;
    boolean running = true;

    public Player(int x, int y, int PADDLE_WIDTH,int PADDLE_HEIGHT, int id){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.score = 0;
        this.id=id;

    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean getRunning() {
        return running;
    }

    public void setYDirection(int y){
        yVelocity = y;
    }

    public void move(){
        y+=yVelocity;
    }

    public void incrementScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public void draw(Graphics g){
        if(id==1){
            g.setColor(Color.blue);
        }else if(id==2){
            g.setColor(Color.red);
        }
        g.fillRect(x,y,width,height);
    }

    public void setKeyPressed(int i){
        switch (i) {
            case KeyEvent.VK_W -> {
                setYDirection(-speed);
                move();
            }
            case KeyEvent.VK_S -> {
                setYDirection(speed);
                move();
            }
        }
    }

    public void setKeyReleased(){
        setYDirection(0);
        move();
    }
}
