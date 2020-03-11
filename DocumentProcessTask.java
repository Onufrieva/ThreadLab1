import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

class DocumentProcessTask extends RecursiveTask<LineTaskResult> {
    private final Document processDocument;

    public DocumentProcessTask(Document document){
        processDocument = document;
    }

    @Override
    protected LineTaskResult compute() {
        var result = new LineTaskResult();

        List<RecursiveTask<LineTaskResult>> tasks = new LinkedList<>();

        for (String line : processDocument.getLines()) {
           LineProcessTask task = new LineProcessTask(line);
            tasks.add(task);
            task.fork();
        }

        for (RecursiveTask<LineTaskResult> task : tasks) {
            var taskResult = task.join();
            result.increaseSymbolsCount(taskResult.getSymbolsCount());
            result.increaseWordsCount(taskResult.getWordsCount());
            result.words.addAll(taskResult.words);
        }

        return result;
    }
}