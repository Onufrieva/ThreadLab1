public class BallThread  extends Thread{
    String str;
    BallThread(String str){
        this.str = str;
    }
    synchronized void khz(){
        try{
        System.out.print(str);
        notifyAll();
        wait();


    } catch(InterruptedException ex){
        System.out.println(Thread.currentThread().getState());
    }
    }

    @Override
    public void run() {

        while (!(1 == 0)) {
            khz();
        }
    }
}
