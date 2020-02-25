public class UnsynchBankTest {

    public static final int NACCOUNTS = 10;
    public static final int INITIAL_BALANCE = 10000;
    public static void main(String[] args) {
        // Створити банк з 10 рахунками та балансом 10000
        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
        int i;
        for (i = 0; i < NACCOUNTS; i++){
            TransferThread t = new TransferThread(b, i, INITIAL_BALANCE);
            t.setPriority(Thread.NORM_PRIORITY + i % 2);
            t.start () ;
        }
    }
}