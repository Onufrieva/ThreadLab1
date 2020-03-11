import java.util.LinkedList;
import java.util.List;

public class LineTaskResult {
    private long symbolsCount;
    private long wordsCount;
    public List<String> words = new LinkedList<>();

    public LineTaskResult(){}

    public LineTaskResult(long symbolsCount, long wordsCount){
        this.symbolsCount = symbolsCount;
        this.wordsCount= wordsCount;
    }

    public synchronized void increaseSymbolsCount(long amount){
        symbolsCount +=amount;
    }

    public synchronized void increaseWordsCount(long amount){
        wordsCount +=amount;
    }

    public long getSymbolsCount(){
        return symbolsCount;
    }

    public long getWordsCount(){
        return wordsCount;
    }

    public void storeData(String word){
            symbolsCount += word.length();
            wordsCount++;
            words.add(word);
    }
}