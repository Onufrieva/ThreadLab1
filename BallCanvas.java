import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallCanvas extends JPanel {

    private ArrayList<Ball> balls = new ArrayList<>();

    public int counter=0;
public int gse(){
    return balls.size();
}
    public void add(Ball b){
        this.balls.add(b);
    }
    public void delete(Ball b){
        this.balls.remove(b);
        counter++;
}
    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
            for(int i=0; i<balls.size();i++){
            Ball b = balls.get(i);
            b.draw(g2);
        }
    }
}
