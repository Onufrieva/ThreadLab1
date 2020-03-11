public class WordCounter {
    public static LineTaskResult lengthCount(String line) {
        LineTaskResult result = new LineTaskResult();
        line.replaceAll("[^\\w\\s]|\"^[а-я]+\\\\s[а-я]+\\\\s[а-я]+$\"", " ");
        line.replaceAll(" +", " ");
        line.trim();
        String[] words = line.split(" ");

        for (String word : words) {
           result.storeData(word);
        }

        return result;
    }

    public static  LineTaskResult CountSymbols(Document document){
        var result = new LineTaskResult();
        for (String line : document.getLines()) {
           var res = WordCounter.lengthCount(line);
            result.increaseSymbolsCount(res.getSymbolsCount());
            result.increaseWordsCount(res.getWordsCount());
            result.words.addAll(res.words);
        }

        return result;
    }
}