import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static ForkJoinPool forkJoinPool = new ForkJoinPool();
    public static void main(String[] args) throws IOException {
        var internalDirectoryPath = System.getProperty("user.dir");
        File folder = new File(internalDirectoryPath);
        List<Document> documents = new LinkedList<>();
        for(File file : folder.listFiles()){
            if(Document.getFileExtension(file).equals("txt")){
                documents.add(Document.fromFile(file));
            }
        }

        var keywords = new LinkedList<String>() {
            {
                add("parallel");
                add("pattern");
                add("technology");
                add("computing");
                add("machine");
            }
        };

        var results = forkJoinPool.invoke(new ThemeSearchTask(documents, keywords));

        for(SearchTaskResult result : results){
            System.out.println("File name: " + result.document.name + " | Words count: " + result.wordsCount + " | Matched: " + result.wordsMatch + " | Match percent: " + new DecimalFormat("#0.0000").format(result.matchPercent* 100));
        }
    }
}
