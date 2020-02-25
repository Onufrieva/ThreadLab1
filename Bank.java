public class Bank {
    public static final int NTEST = 10000;
    private final int[] accounts;               // масив рахунків
    private long ntransacts = 0;

    public Bank(int n, int initialBalance){
        accounts = new int[n];                  // створити рахунок в банку
        int i;
        for (i = 0; i < accounts.length; i++)   // нарахувати на кожний рахунок 10 000
            accounts[i] = initialBalance;
        ntransacts = 0;

    }
    public void transfer(int from, int to, int amount) throws InterruptedException{
        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        if (ntransacts % NTEST == 0)
            test();

    }
    public void test(){
        int sum = 0;
        for (int i = 0; i < accounts.length; i++)
            sum += accounts[i] ;
        System.out.println("Transactions: " + ntransacts
                + " Sum: " + sum);

    }
    public int size(){
        return accounts.length;
    }
}
