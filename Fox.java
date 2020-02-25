public class Fox {
    public static MyMatrix foxMultiply(final MyMatrix aMatrix, final MyMatrix bMatrix, final int proc) {
        final int aRows = aMatrix.getRows();
        final int aCols = aMatrix.getCols();
        final int bRows = bMatrix.getRows();
        final int bCols = bMatrix.getCols();


        final double procRoot = Math.sqrt(proc);
        final int blocksSqrt = (int) procRoot;

        final double blockSizeRaw = aRows / procRoot;
        final int blockSize = (int) blockSizeRaw + (blockSizeRaw % 1 > 0 ? 1 : 0);
        final int[][] multiplier1 = aMatrix.getMatrix();
        final int[][] multiplier2 = bMatrix.getMatrix();
        final int[][] product = new int[aRows][aCols];


        Thread[][] tArray = new Thread[blocksSqrt][blocksSqrt];
        for (int i = 0; i < blocksSqrt; i++) {
            for (int j = 0; j < blocksSqrt; j++) {
                tArray[i][j] = new FoxThread(multiplier1, multiplier2, product, i, j, blockSize, blocksSqrt);
            }
        }

        MyMatrix cMatrix = null;

        try {
            for (int i = 0; i < blocksSqrt; i++) {
                for (int j = 0; j < blocksSqrt; j++) {
                    tArray[i][j].start();
                }
            }

            for (int i = 0; i < blocksSqrt; i++) {
                for (int j = 0; j < blocksSqrt; j++) {
                    tArray[i][j].join();
                }
            }
        } catch (InterruptedException ex) {
            System.err.println("Помилка в роботі потоків, назапланована зупинка програми.");
            System.exit(0);
        } finally {
            cMatrix = new MyMatrix(product);
        }

        return cMatrix;

    }
}
