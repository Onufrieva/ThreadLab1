public class BlockThread implements Runnable {
    MyMatrix A;
    MyMatrix B;
    MyMatrix C;
    private int start;          // номер рядка, з якого починається розрахунок в даній підзадачі
    private int finish;         // номер рядка, з якого починається наступна підзадача (кінець)

    public BlockThread(MyMatrix A, MyMatrix B, MyMatrix C, int start, int finish) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.start = start;
        this.finish = finish;
    }

    @Override
    public void run() {
        // Для кожного рядка підзадачі {ai*}
        for(int i = this.start; i < this.finish; i++) {
            // Для кожного стовпця матриці B {b*j}
            for(int j = 0; j < this.B.getCols(); j++) {
                // Для кожного елемента в стовпці матриці B  {bkj}
                for(int k = 0; k < this.B.getRows(); k++) {
                    int value = C.getElement(i, j) + A.getElement(i, k) * B.getElement(k, j);
                    C.setElement(i, j, value);
                }
            }
        }
    }
}
