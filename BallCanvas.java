import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<>();


    public int counter=0;

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
        g2.setColor(Color.black);
        g2.fill(new Ellipse2D.Double(100,50,25,25));
            g.setColor(Color.black);
            g.drawString(" Counter: "+Integer.toString(counter),10,250);

        for(int i=0; i<balls.size();i++){
            Ball b = balls.get(i);
            b.draw(g2);
        }
    }
}
