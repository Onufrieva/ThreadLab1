public class MatrixTaskResult {
    public  Matrix cBlock;
    public int index;
    public int length;

    public MatrixTaskResult(int i, int l, Matrix matrix){
        cBlock = matrix;
        index = i;
        length = l;
    }
}
