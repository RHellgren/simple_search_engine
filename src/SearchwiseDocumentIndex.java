import java.util.HashMap;

/**
 * Created by Daredevil on 2017-03-26.
 */
public class SearchwiseDocumentIndex {
    private String documentName;
    private HashMap<String, Integer> index;
    private int termsInDocument;

    public SearchwiseDocumentIndex(String documentName, HashMap<String, Integer> index, int termsInDocument) {
        this.documentName = documentName;
        this.index = index;
        this.termsInDocument = termsInDocument;
    }

    public String getDocumentName() { return documentName; }

    public HashMap<String, Integer> getIndex() { return index; }

    public int getTermsInDocument() { return termsInDocument; }

    public Integer getCountForWord(String word) {
        if (index.containsKey(word))
            return index.get(word);
        else
            return 0;
    }
}
