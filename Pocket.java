import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Pocket  {
    private Component canvas;
    private static final int XSIZE = 25;
    private static final int YSIZE = 25;
    public int x = 100;
    public int  y= 50;

    public Pocket(Component c){
        this.canvas = c;
    }

    public static void f(){
        int a = 0;
    }

    public void draw (Graphics2D g2){
        g2.setColor(Color.black);
        g2.fill(new Ellipse2D.Double(x,y,XSIZE,YSIZE));

    }


}
