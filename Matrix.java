import java.security.InvalidParameterException;
import java.util.concurrent.ForkJoinPool;

public class Matrix {

    public int rows;
    public int columns;
    public double[][] matrixValues;

    public Matrix(int r, int c) {
        this.matrixValues = new double[r][c];
        this.rows = r;
        this.columns = c;
    }

    public Matrix(double[][] data) {
        this.matrixValues = data;
        this.rows = data.length;
        this.columns = data[0].length;
    }


    public static Matrix StripedAlgorithm(Matrix aMatrix, Matrix bMatrix, TimeWrapper timer)
    {
        if(aMatrix.columns !=  bMatrix.rows) throw new InvalidParameterException();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new MatrixProcessTask(aMatrix, bMatrix, timer));
    }

    public Matrix MultiplyMatrix(Matrix matrix, Matrix rightMatrix)
    {
        if(matrix.columns != rightMatrix.rows) throw new InvalidParameterException();
        var resultMatrix = new Matrix(matrix.rows, rightMatrix.columns);
        for (var i = 0; i < matrix.rows; i++)
        {
            for (var j = 0; j < matrix.columns; j++)
            {
                for (var k = 0; k < rightMatrix.columns; k++)
                {
                    resultMatrix.matrixValues[i][j] += matrix.matrixValues[i][k] * rightMatrix.matrixValues[k][j];
                }
            }
        }

        return resultMatrix;
    }

    public Matrix CutSubmatrix(int i, int j, int amount){
        var resultMatrix = new Matrix(amount,amount);
        var values = new double[amount][amount];
        resultMatrix.matrixValues = values;
        for(int k=0;k<amount;k++){
            for(int l=0;l<amount;l++)
            {
                values[k][l] = this.matrixValues[k+i][l+j];
            }
        }
        return resultMatrix;
    }

    public boolean Equal(Matrix matrixToCompare){
        if(matrixToCompare.rows != this.rows && matrixToCompare.columns != this.columns) return false;
        for (int i =0;i<this.rows;i++){
            for (int j =0;j<this.columns;j++){
                if(this.matrixValues[i][j] != matrixToCompare.matrixValues[i][j]) return false;
            }
        }

        return true;
    }

    public static Matrix Random(int rows, int columns, int max){
        var a = new Matrix(rows, columns);
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                a.matrixValues[i][j] = 1;// new Random().nextInt(max) + 1;
            }
        }

        return a;
    }
}