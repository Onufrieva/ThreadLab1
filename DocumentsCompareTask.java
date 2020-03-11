import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class DocumentsCompareTask extends RecursiveTask< List<String>> {
    private Document firstFile;
    private Document secondFile;

    public DocumentsCompareTask(Document file, Document fileToCompare){
        firstFile = file;
        secondFile = fileToCompare;
    }

    @Override
    protected List<String> compute() {
        List<String> similarWords = new LinkedList<>();

        List<RecursiveTask<List<String>>> tasks = new LinkedList<>();
        var compareLines = secondFile.getLines();
        for (String line : firstFile.getLines()) {
            TextCompareTask task = new TextCompareTask(line, compareLines);
            tasks.add(task);
            task.fork();
        }

        for (RecursiveTask<List<String>> task : tasks) {
            var taskResult = task.join();
            similarWords.addAll(taskResult);
        }

        return  similarWords.stream().distinct().filter(s->s.length() > 3).collect(Collectors.toList());
    }
}
