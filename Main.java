import java.io.File;

public class Main {
    private static long start;

    public static void main(String[] args) {
        // Якщо потрібно рандомно згенерувати матриці, поставте false
        boolean isFromFile = false;
        int processors = 4;

        MyMatrix A = MatrixOp.createMatrix(5, 4, isFromFile, new File("A.txt"));
        MyMatrix B = MatrixOp.createMatrix(4, 3, isFromFile, new File("B.txt"));

        if (A.getCols() != B.getRows()) {
            System.out.println("Матриці не можуть бути перемножені");
            System.exit(0);
        }

        System.out.println("---------- Звичайне множення матриць ----------");
        MyMatrix simpleC = new MyMatrix(A.getRows(), B.getCols(), 0);
        startNanoTimer();
        MatrixOp.simpleMultiplying(A, B, simpleC);
        finishNanoTimer();
        MatrixOp.writeInFile(simpleC, new File("simpleC.txt"));

        System.out.println("---------- Cтрічковий алгоритм множення матриць ----------");
        MyMatrix blockC = new MyMatrix(A.getRows(), B.getCols(), 0);
        startNanoTimer();
        MatrixOp.blockMultiply(A, B, blockC, processors);
        finishNanoTimer();
        MatrixOp.writeInFile(blockC, new File("blockC.txt"));
    }

    public static void startNanoTimer() {
        start = System.nanoTime();
    }

    public static void finishNanoTimer() {
        long finish = System.nanoTime();
        long time = finish - start;
        System.out.println("Тривалість обчислень: " + (time / 1000000000) + " сек, " +
                (time / 1000000 % 1000) + " мілісек, " + (time / 1000 % 1000) + " мікросек");
        System.out.println("Тривалість обчислень в наносекундах: " + time);
        start = 0;
    }
}