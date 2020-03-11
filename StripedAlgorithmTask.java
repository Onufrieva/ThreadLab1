import java.util.concurrent.RecursiveTask;

public class StripedAlgorithmTask extends RecursiveTask<MatrixTaskResult> {
    public  Matrix cBlock = null;
    public int index;
    public int length;
    public  int aMatrixIndex;
    public  int bMatrixIndex;
    public int averageLength;
    public  MatrixDataContainer container;

    public StripedAlgorithmTask(int index, int length, int aIndex, int bIndex, int averageLength, MatrixDataContainer container){
        this.container = container;
        aMatrixIndex = aIndex;
        bMatrixIndex = bIndex;
        this.index = index;
        this.length = length;
        this.averageLength = averageLength;
    }

    @Override
    protected MatrixTaskResult compute() {
        var a = container.matricesBlocks[aMatrixIndex][0][0];
        var b = container.matricesBlocks[bMatrixIndex][0][0];

        if (cBlock == null) {
           cBlock = new Matrix(length, a.columns);
        }

        int iIndex = 0;
        for (int i = index * averageLength; i < averageLength *index + length; i++) {
            var aRow = a.matrixValues[i];
            for (int j = 0; j < aRow.length; j++) {
                var bRow = b.matrixValues[j];
                for (int k = 0; k < bRow.length; k++) {
                   cBlock.matrixValues[iIndex][k] += aRow[j] *  bRow[k];
                }
            }
            iIndex++;
        }

        return new MatrixTaskResult(index, length, cBlock);
    }
}
