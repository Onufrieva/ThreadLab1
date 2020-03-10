import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ThemeSearchTask extends RecursiveTask<List<SearchTaskResult>> {
    private List<Document> documents;
    private List<String> keywords;
    public ThemeSearchTask(List<Document> documents, List<String> keywords){
        this.documents = documents;
        this.keywords = keywords;
    }
    @Override
    protected List<SearchTaskResult> compute() {
        List<SearchTaskResult> list = new LinkedList<>();
        List<RecursiveTask<SearchTaskResult>> tasks = new LinkedList<>();

        for (Document document : documents) {
            DocumentSearchTask task = new DocumentSearchTask(document, keywords);
            tasks.add(task);
            task.fork();
        }

        for (RecursiveTask<SearchTaskResult> task : tasks) {
            var taskResult = task.join();
            list.add(taskResult);
        }

        Collections.sort(list);
        return list;
    }
}
