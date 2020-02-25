public class Main {
    private static long start;

    public static void main(String[] args) {

        int[] processors = {2, 4, 8, 16};
        int[] sizes = {500, 1000, 1500, 2000, 2500, 3000, 4000, 5000};

        for (int size : sizes) {
            MyMatrix A = new MyMatrix(size, size);
            MyMatrix B = new MyMatrix(size, size);
            System.out.println("РОЗМІР МАТРИЦЬ: " + size);
            System.out.println("---------- Звичайне множення матриць ----------");
            MyMatrix simpleC = new MyMatrix(A.getRows(), B.getCols(), 0);
            start = System.nanoTime();
            MatrixOp.simpleMultiplying(A, B, simpleC);
            finishNanoTimer();
            System.out.println("---------- Стрічковий алгоритм множення матриць ----------");
            for (int p : processors) {
                System.out.println("КІЛЬКІСТЬ ПОТОКІВ: " + p);
                MyMatrix blockC = new MyMatrix(A.getRows(), B.getCols(), 0);
                start = System.nanoTime();
                MatrixOp.blockMultiply(A, B, blockC, p);
                finishNanoTimer();
            }
            System.out.println("\n---------- Алгоритм Фокса ----------");
            for (int p : processors) {
                System.out.println("КІЛЬКІСТЬ ПОТОКІВ: " + p);
                start = System.nanoTime();
                Fox.foxMultiply(A, B, p);
                finishNanoTimer();
            }
        }

    }

    public static void finishNanoTimer() {
        long finish = System.nanoTime();
        long time = finish - start;
        System.out.println("Тривалість обчислень: " + (time / 1000000) + " мілісек, " + (time / 1000 ) + " мікросек");
        System.out.println("Тривалість обчислень в наносекундах: " + time);
        start = 0;
    }
}
