import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MatrixProcessTask extends RecursiveTask<Matrix> {
    public Matrix aMatrix;
    public Matrix bMatrix;
    public Matrix finalMatrix;
    public TimeWrapper timer;

    public MatrixProcessTask(Matrix a, Matrix b, TimeWrapper timer){
        aMatrix =a;
        bMatrix=b;
        this.timer = timer;
    }

    @Override
    protected Matrix compute() {
        finalMatrix = new Matrix(aMatrix.rows, bMatrix.columns);
        var n = aMatrix.rows;
        var p = n;
        var q = 1;

        MatrixDataContainer container = new MatrixDataContainer(2, 1,1);
        final int aBlocksIndex = 0;
        final int bBlocksIndex = 1;
        container.matricesBlocks[aBlocksIndex][0][0] = aMatrix;
        container.matricesBlocks[bBlocksIndex][0][0] = bMatrix;

        List<RecursiveTask<MatrixTaskResult>> tasks = new LinkedList<>();
        for(int i=0;i<p;i++){
            if(i== p-1){
                var length = 1;
                var task = new StripedAlgorithmTask(i, length, aBlocksIndex, bBlocksIndex,1,container);
                tasks.add(task);
                task.fork();
            }
            else{
                var task = new StripedAlgorithmTask(i, 1, aBlocksIndex, bBlocksIndex,1,container);
                tasks.add(task);
                task.fork();
            }
        }

        var start = System.nanoTime();
        for (RecursiveTask<MatrixTaskResult> task : tasks) {
            var taskResult = task.join();
            var iIndex = 0;
            for(int i = taskResult.index * q;i < taskResult.index * q + taskResult.length;i++){
                if(i==500){
                    var c =0;
                }
                for (int j=0;j<finalMatrix.columns;j++){
                    finalMatrix.matrixValues[i][j] = taskResult.cBlock.matrixValues[iIndex][j];
                }
                iIndex++;
            }
        }

        timer.elapsedTime = System.nanoTime() - start;
        return finalMatrix;
    }
}
