import java.awt.*;

public class Score extends Rectangle {

    static int WIDTH=1000;
    static int HEIGHT=(int)(WIDTH*0.5555);
    Player player1;
    Player player2;

    Score(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN,60));

        g.drawLine(WIDTH/2,0,WIDTH/2,HEIGHT);

        g.drawString(String.valueOf(player1.getScore()/10)+String.valueOf(player1.getScore()%10),(WIDTH/2)-85,50);
        g.drawString(String.valueOf(player2.getScore()/10)+String.valueOf(player2.getScore()%10),(WIDTH/2)+20,50);
    }
}