public class Cou extends Counter{
    public Cou() {
    }
    @Override
    public synchronized void increment() {
        super.increment();
    }

    @Override
    public synchronized void decrement() {
        super.decrement();
    }
}
