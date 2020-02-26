import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    ReentrantLock lock = new ReentrantLock();
    public static final int NTEST = 10000;
    private final AtomicIntegerArray accounts;               // масив рахунків
    private AtomicLong ntransacts = new AtomicLong();

    public Bank(int n, int initialBalance){
        accounts = new AtomicIntegerArray(n);                  // створити рахунок в банку
        int i;
        for (i = 0; i < accounts.length(); i++)   // нарахувати на кожний рахунок 10 000
            accounts.set(i,initialBalance);
        ntransacts = new AtomicLong(0);

    }

    public void transfer(int from, int to, int amount) throws InterruptedException{
        lock.lock();
        try {
            if (accounts.get(from)>= amount) {
                accounts.getAndAdd(from,amount);
                accounts.getAndAdd(to,-amount);
                ntransacts.incrementAndGet();
            }
            if (ntransacts.get() % NTEST == 0) test();
        }
        finally {
            lock.unlock();
        }
    }

    public void test(){
        int sum = 0;
        for (int i = 0; i < accounts.length(); i++)
            sum += accounts.get(i);
        System.out.println("Transactions: " + ntransacts
                + " Sum: " + sum);

    }
    public int size(){
        return accounts.length();
    }
}
