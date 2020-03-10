import java.util.List;
import java.util.concurrent.RecursiveTask;

public class LineSearchTask extends RecursiveTask<LineSearchResult> {
    private String line;
    private List<String> keywords;

    public LineSearchTask(String line, List<String> keywords){
        this.line = line;
        this.keywords = keywords;
    }

    @Override
    protected LineSearchResult compute() {
        LineSearchResult result = new LineSearchResult();

        line.replaceAll("[^\\w\\s]|\"^[а-я]+\\\\s[а-я]+\\\\s[а-я]+$\"", " ");
        line.replaceAll(" +", " ");
        line.trim();
        var words = line.split(" ");

        for(String word : words){
            if(keywords.contains(word)){
                result.wordsMatch++;
            }

            result.wordsCount++;
        }

        return result;
    }
}
