public class Bounce {

    public static void main(String[] args) {
        Cou counters = new Cou();
        BallThread decrement = new BallThread(counters::decrement);
        BallThread increment = new BallThread(counters::increment);
        increment.start();
        decrement.start();
        try {
            increment.join();
            decrement.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int count = counters.getCount();
        System.out.println(count);
    }
}