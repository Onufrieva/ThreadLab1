public class SearchTaskResult implements Comparable<SearchTaskResult> {
    public Document document;
    public Long wordsCount = 0L;
    public Long wordsMatch = 0L;
    public double matchPercent = 0D;

    @Override
    public int compareTo(SearchTaskResult d) {
        return this.matchPercent > d.matchPercent ? -1 : this.matchPercent == d.matchPercent ? 0 : 1;
    }
}
