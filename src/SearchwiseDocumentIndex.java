import java.util.HashMap;

/**
 * Created by Daredevil on 2017-03-26.
 */
public class SearchwiseDocumentIndex {
    private String documentName;
    private HashMap<String, Integer> index;

    public SearchwiseDocumentIndex(String documentName, HashMap<String, Integer> index) {
        this.documentName = documentName;
        this.index = index;
    }

    public void setDocumentName(String documentName) { this.documentName = documentName; }

    public void setIndex(HashMap<String, Integer> index) { this.index = index; }

    public String getDocumentName() { return documentName; }

    public HashMap<String, Integer> getIndex() { return index; }

    public Integer getCountForWord(String word) {
        if (index.containsKey(word))
            return index.get(word);
        else
            return 0;
    }
}
