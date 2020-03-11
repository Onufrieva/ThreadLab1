import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
         var timer = new TimeWrapper();
         var a = Matrix.Random(1000,1000,1);
        var b = Matrix.Random(1000,1000,1);
        var resultS = Matrix.StripedAlgorithm(a,b, timer);
        var t = ((double)timer.elapsedTime)/ 1000000000;
        System.out.println("Time consumed: " + t);
    }
}
