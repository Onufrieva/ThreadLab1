public class BallThread extends Thread {
    Runnable function;

    public BallThread(Runnable function) {
        this.function = function;
    }

    @Override
    public void run() {
//        for (int i = 0; i < 100000; i++) {
//            this.function.run();
//        }
      try {
            for (int i = 0; i < 100000; i++) {
                this.function.run();
                sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
