public class BallThread  extends Thread{
    String str;
    BallThread(String str){
        this.str = str;
    }
    synchronized
    @Override
    public void run(){
        try{
            while (!(1==0)){
                System.out.print(str);
                notifyAll();
                wait();

            }

        } catch(InterruptedException ex){
            System.out.println(Thread.currentThread().getState());
        }

    }
}
