import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {

    public static final int NTEST = 10000;
    private final AtomicIntegerArray accounts;               // масив рахунків
    private AtomicLong ntransacts = new AtomicLong();

    public Bank(int n, int initialBalance){
        accounts = new AtomicIntegerArray(n);                  // створити рахунок в банку
        int i;
        for (i = 0; i < accounts.length(); i++)   // нарахувати на кожний рахунок 10 000
            accounts.set(i,initialBalance);
        ntransacts.set(0);

    }

    public void transfer(int from, int to, int amount) throws InterruptedException{



                accounts.getAndAdd(from,amount);
                accounts.getAndAdd(to,-amount);
                ntransacts.set(ntransacts.get()+1);

            if (ntransacts.get() % NTEST == 0) test();


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
