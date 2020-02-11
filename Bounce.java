public class Bounce {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            BallThread thread = new BallThread("-");
            thread.start();
            BallThread thread1 = new BallThread("|");
            thread1.start();
        }

    }
}