import java.util.ArrayList;

public class BallThread  extends Thread{
    private Ball b;
private int x = 0;
private int y = 0;
public int tt=0;
int i;
BallCanvas canvas;
    ArrayList<BallThread> ballThreads = new ArrayList<>();

    public  BallThread(Ball ball, BallCanvas c, ArrayList<BallThread> ballThreads){
        b = ball;
        canvas = c;
        this.ballThreads = ballThreads;
    }

    @Override
    public void run(){

        try{
            if(ballThreads.size()==1){
            join();
          for (int i = 0; i <1000 ; i++) {
                    b.move();
                    System.out.println("Thread name = "
                            + Thread.currentThread().getName());
                    Thread.sleep(5);
                }
          canvas.delete(b);
            }

        } catch(InterruptedException ex){
            System.out.println(Thread.currentThread().getState());
        }

    }
}
