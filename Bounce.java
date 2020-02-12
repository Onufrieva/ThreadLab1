public class Bounce {

    public static void main(String[] args) {
        BallThread thread;
        for (int i = 0; i < 2; i++) {
              if (i==0) {
                  thread = new BallThread("-", i);
                  thread.start();
              }else {
                  thread = new BallThread("|", i);
                  thread.start();
              }
        }
    }
}