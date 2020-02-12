public class BallThread  extends Thread{

    private Ball b;
private int x=0;
private int y =0;
public int tt=0;
BallCanvas canvas;


    public void getPocket(int x, int y){
       this.x=x;
       this.y=y;
    }

    public BallThread(Ball ball, BallCanvas c){
        b = ball;
        canvas = c;

    }

    @Override
    public void run(){
        try{
            while (!((b.x>=x-10 && b.x<=x+10 )&&(b.y>=y-10 && b.y<=y+10))){
                b.move();
                System.out.println("Thread name = "
                        + Thread.currentThread().getName());
                Thread.sleep(5);
            }
            canvas.delete(b);
            canvas.repaint();
           // Thread.currentThread().interrupt();
            Thread.currentThread().sleep(1);

        } catch(InterruptedException ex){

            System.out.println(Thread.currentThread().getState());
        }

    }
}
