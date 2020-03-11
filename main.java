import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.ForkJoinPool;

public class main {
    public static void main(String[] args) throws IOException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        var internalDirectoryPath = System.getProperty("user.dir");
        var path = Paths.get(internalDirectoryPath, "firstTestFile.txt").toString();
        File file = new File(path);
        Document document;
        document = Document.fromFile(file);
        var start = System.currentTimeMillis();
        var c = WordCounter.CountSymbols(document);
        System.out.println("Average symbols count in words: " + (double)c.getSymbolsCount() / c.getWordsCount());

        var time = (double)(System.currentTimeMillis() - start);
        System.out.println("Time consumed: " + time);

        start = System.currentTimeMillis();
        var res = forkJoinPool.invoke(new DocumentProcessTask(document));
        time = (double)(System.currentTimeMillis() - start);
        System.out.println("Time consumed: " + time);
        var averageSymbols = (double)res.getSymbolsCount() / res.getWordsCount();
        System.out.println("Average symbols count in words: " + averageSymbols);
        double d = 0;
        for(String word: c.words){
          var p = (double)c.words.stream().filter(s->s.length() == word.length()).count() / c.words.size();
          var g = p * Math.pow(word.length() - averageSymbols, 2);
          d+=g;
        }
        d/=c.words.size();
        System.out.println("Dispersion: " + d);
    }
}
