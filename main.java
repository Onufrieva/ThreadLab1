import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.ForkJoinPool;

public class main {
    private static ForkJoinPool forkJoinPool = new ForkJoinPool();
    public static void main(String[] args) throws IOException {
        var internalDirectoryPath = System.getProperty("user.dir");
        var path = Paths.get(internalDirectoryPath, "firstTestFile.txt").toString();
        Document firstDocument = Document.fromFile(new File(path));
        path = Paths.get(internalDirectoryPath, "secondTestFile.txt").toString();
        Document secondDocument = Document.fromFile(new File(path));

        var similarWords = forkJoinPool.invoke(new DocumentsCompareTask(firstDocument, secondDocument));

        System.out.println("Similar words count: " + similarWords.size());
        for(String word: similarWords){
            System.out.println(word);
        }
    }
}
