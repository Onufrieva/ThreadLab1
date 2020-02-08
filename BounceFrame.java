import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {

    private BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
Color c1 = new Color (255,255,0);
    Color c2 = new Color (0,100,255);
    Color c3 = new Color (0,102,50);
    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");





        this.canvas = new BallCanvas();



        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        this.canvas.setBackground(c3);
        Container content = this.getContentPane();

        content.add(this.canvas, BorderLayout.CENTER);



        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");
        JLabel textPane = new JLabel("tt");

        Pocket pocket = new Pocket(canvas);
        canvas.addPocket(pocket);
        Counter con = new Counter(canvas);
        canvas.addC(con);

        buttonStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas,c1);
                canvas.add(b);
                BallThread thread = new BallThread(b, canvas);
                thread.getPocket(pocket.x,
                        pocket.y);
                thread.setPriority(Thread.MAX_PRIORITY);
                thread.start();
                System.out.println("Thread name = " + thread.getName());

                canvas.repaint();

            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas,c2);
                canvas.add(b);
                BallThread thread = new BallThread(b, canvas);
                thread.getPocket(pocket.x,
                        pocket.y);
                thread.setPriority(Thread.MIN_PRIORITY);
                thread.start();
                System.out.println("Thread name = " + thread.getName());

                canvas.repaint();
            }
        });
        buttonPanel.add(textPane );
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }

}
