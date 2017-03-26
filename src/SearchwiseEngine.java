import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Daredevil on 2017-03-26.
 */
public class SearchwiseEngine {
    ArrayList<SearchwiseDocumentIndex> dictionary = new ArrayList<>();

    public void parseSingleFile(String fileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        String[] words = content.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        SearchwiseDocumentIndex document = new SearchwiseDocumentIndex(fileName, createNewIndex(words));
        dictionary.add(document);
    }

    public void parseMultipleFiles(String pathToFolder) throws IOException {
        System.out.println(Paths.get(pathToFolder));
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

    public List<String> searchForString(String searchWord) {
        List<String> documentsFound = new ArrayList<>();
        for (SearchwiseDocumentIndex document : dictionary) {
            Integer count = document.getCountForWord(searchWord);
            if (count > 0)
                documentsFound.add(document.getDocumentName() + " has " + count + " occurences");
        }

        return documentsFound;
    }


}
