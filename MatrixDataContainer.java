public class MatrixDataContainer {
    public Matrix[][][] matricesBlocks;

    public MatrixDataContainer(int n, int r, int c){
        matricesBlocks = new Matrix[n][r][c];
    }
}
