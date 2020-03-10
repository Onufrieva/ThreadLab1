import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DocumentSearchTask extends RecursiveTask<SearchTaskResult> {
    private Document document;
    private List<String> keywords;
    public DocumentSearchTask(Document document, List<String> keywords){
        this.document=document;
        this.keywords=keywords;
    }

    @Override
    protected SearchTaskResult compute() {
        SearchTaskResult result = new SearchTaskResult();

        List<RecursiveTask<LineSearchResult>> tasks = new LinkedList<>();

        for (String line: document.getLines()) {
            LineSearchTask task = new LineSearchTask(line, keywords);
            tasks.add(task);
            task.fork();
        }

        for (RecursiveTask<LineSearchResult> task : tasks) {
            var taskResult = task.join();
            result.wordsCount += taskResult.wordsCount;
            result.wordsMatch += taskResult.wordsMatch;
        }

        result.document = document;
        result.matchPercent = (double)result.wordsMatch / result.wordsCount;
        return result;
    }
}
