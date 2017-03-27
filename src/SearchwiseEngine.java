import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Daredevil on 2017-03-26.
 */
public class SearchwiseEngine {
    ArrayList<SearchwiseDocumentIndex> dictionary = new ArrayList<>();

    /**
     * Takes a specified file (or filepath) and cleans the content (removing punctionation),
     * then creates a specific search index for that file and adds it to the dictionary.
     * @param fileName the file that should be parsed and added to the dictionary.
     * @throws IOException if the file does not exist, the path is incorrect or there is other I/O issues.
     */
    public void parseSingleFile(String fileName) throws IOException {
        Path pathToFile = Paths.get(fileName);
        String content = new String(Files.readAllBytes(pathToFile));
        String[] words = content.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        SearchwiseDocumentIndex document = new SearchwiseDocumentIndex(pathToFile.getFileName().toString(), createNewIndex(words), words.length);
        dictionary.add(document);
    }

    /**
     * Takes a path to a folder then parses all files within that folder, avoiding hidden and non-regular files.
     * @param pathToFolder the path to the folder with the files to be parsed.
     * @throws IOException if the path is incorrect or there is other I/O issues.
     */
    public void parseMultipleFiles(String pathToFolder) throws IOException {
        Stream<Path> paths = Files.walk(Paths.get(pathToFolder));
        paths.forEach(path -> {
            try {
                if (Files.isRegularFile(path) && !Files.isHidden(path))
                    parseSingleFile(path.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Takes a (cleaned) list of words and creates an index with unique words and their number of occurrences.
     * @param words the list of all the words in the document, including duplicates.
     * @return the index containing unique words in the document and their corresponding occurrences within that document.
     */
    private HashMap<String, Integer> createNewIndex(String[] words) {
        HashMap<String, Integer> index = new HashMap<>();
        for (String word : words) {
            if (index.containsKey(word))
                index.put(word, index.get(word) + 1);
            else
                index.put(word, 1);
        }
        return index;
    }

    /**
     * Search for the given string (searchWord) in the documents that have been parsed thus far.
     * Returns a list of documents containing the word, sorted by TF-IDF value.
     * @param searchWord the word to search for.
     * @return a list of document names (Strings) containing the word, sorted by TF-IDF.
     */
    public List<String> searchForString(String searchWord) {
        // Get the documents containing the word and their corresponding TF.
        HashMap<String, Double> documentsContainingWordTF = new HashMap<>();
        for (SearchwiseDocumentIndex document : dictionary) {
            Integer count = document.getCountForWord(searchWord);
            if (count > 0) {
                double tf = (double) count/document.getTermsInDocument();
                documentsContainingWordTF.put(document.getDocumentName(), tf);
            }
        }

        // Calculate the IDF and edit the list of document to show corresponding TF-IDF values.
        HashMap<String, Double> documentsContainingWordTFIDF = new HashMap<>();
        double idf = Math.log((double) dictionary.size()/documentsContainingWordTF.size());
        for (Map.Entry<String, Double> document : documentsContainingWordTF.entrySet())
            documentsContainingWordTFIDF.put(document.getKey(), document.getValue()*idf);

        // Sort the list to show highest TF-IDF first, create the list of Strings to be returned.
        List<String> result = new ArrayList<>();
        documentsContainingWordTFIDF.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> result.add(x.getKey()));

        return result;
    }


}
