public class FoxThread extends Thread {

    private int[][] product;
    private final int[][] multiplier1;
    private final int[][] multiplier2;
    private final int blockSize;
    private final int stepsCount;
    private final int blockStartLine;
    private final int blockStartCol;


    public FoxThread(final int[][] multiplier1, final int[][] multiplier2, final int[][] product, final int procI,
                     final int procJ, final int blockSize, final int stepsCount) {
        this.blockStartLine = procI * blockSize;
        this.blockStartCol = procJ * blockSize;

        this.multiplier1 = multiplier1;
        this.multiplier2 = multiplier2;
        this.product = product;
        this.blockSize = blockSize;
        this.stepsCount = stepsCount;
    }

    @Override
    public void run() {
        // For each step
        for (int step = 0; step < stepsCount; step++) {
            // For each row
            for (int row = 0; row < this.blockSize; row++) {
                // Out of column
                if ((row + this.blockStartLine) >= this.multiplier1.length) {
                    break;
                }
                // For each column
                for (int col = 0; col < this.blockSize; col++) {
                    if ((col + this.blockStartCol) >= this.multiplier1.length) {
                        break;
                    }

                    calculateProductI(step, row, col);
                }
            }
        }
    }

    private void calculateProductI(int step, int row, int col) {
        for (int i = 0; i < this.blockSize; i++) {
            if ((step * this.blockSize + i) >= this.multiplier1.length) {
                break;
            }

            this.product[row + this.blockStartLine][col + this.blockStartCol] +=
                    this.multiplier1[this.blockStartLine + row][step * this.blockSize + i] *
                            this.multiplier2[step * this.blockSize + i][this.blockStartCol + col];
        }
    }
}