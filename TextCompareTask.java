import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class TextCompareTask extends RecursiveTask<List<String>> {
    private String line;
    private List<String> compareText;

    public TextCompareTask(String lineToCompare, List<String> compareText){
        line = lineToCompare;
        this.compareText = compareText;
    }

    @Override
    protected List<String> compute() {
        List<String> similarWords = new LinkedList<>();
        line.replaceAll("[^\\w\\s]|\"^[а-я]+\\\\s[а-я]+\\\\s[а-я]+$\"", " ");
        line.replaceAll(" +", " ");
        line.trim();

        List<String> lineWords = Arrays.asList(line.split(" "));

        for(String line : compareText){
            line.replaceAll("[^\\w\\s]|\"^[а-я]+\\\\s[а-я]+\\\\s[а-я]+$\"", " ");
            line.replaceAll(" +", " ");
            line.trim();

            var compareWords = line.split(" ");

            for(String word : compareWords){
                if(lineWords.contains(word)){
                    similarWords.add(word);
                }
            }
        }

        return similarWords;
    }
}
