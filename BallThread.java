public class BallThread extends Thread {
    private String str;
    public int idThread;
    private static int turn = 1;

    BallThread(String str, int idThread) {
        this.str = str;
        this.idThread = idThread;
    }

    private synchronized void setNewTurn() {
        turn++;
    }


    @Override
    public void run() {
        while (true) {
            try {
                if (idThread == (turn % 2)) {
                    System.out.print(str);
                    setNewTurn();
                } else {
                    sleep(5);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
