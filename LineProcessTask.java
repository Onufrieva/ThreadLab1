import java.util.concurrent.RecursiveTask;

public class LineProcessTask extends RecursiveTask<LineTaskResult> {
    private String line;

    public LineProcessTask(String line){
        this.line = line;
    }

    @Override
    protected LineTaskResult compute() {
        return WordCounter.lengthCount(line);
    }
}
