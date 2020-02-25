import java.util.Random;

public class MyMatrix {
    final Random random = new Random();

    protected int[][] matrix;

    public MyMatrix(int rows, int cols) {
        matrix = new int[rows][cols];
        generate();
    }

    public MyMatrix(int rows, int cols, int i) {
        matrix = new int[rows][cols];
        generate(i);
    }
    public MyMatrix(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }


    public void setElement(int i, int j, int value) {
        matrix[i][j] = value;
    }

    public int getElement(int i, int j) {
        return matrix[i][j];
    }

    public int[][] getMatrix() {
        return matrix;
    }
//
//    public void setMatrix(int[][] matrix) {
//        this.matrix = matrix;
//    }

    public void generate() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = random.nextInt(10);
            }
        }
    }

    public void generate(int value){
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                matrix[i][j] = value;
            }
        }
    }

//    public void print(String str) {
//        System.out.println(str);
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                System.out.print(matrix[i][j] + "\t");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }

    public int getCols(){
        return matrix[0].length;
    }

    public int getRows(){
        return matrix.length;
    }


}
